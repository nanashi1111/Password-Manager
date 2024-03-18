package com.klmobile.passwordmanager

import android.content.Context
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klmobile.passwordmanager.Screens.SCREEN_ACCOUNTS
import com.klmobile.passwordmanager.Screens.SCREEN_CREATE_ACCOUNTS
import com.klmobile.passwordmanager.Screens.SCREEN_LOGIN
import com.klmobile.passwordmanager.Screens.SCREEN_ONBOARDING
import com.klmobile.passwordmanager.Screens.SCREEN_REGISTER
import com.klmobile.passwordmanager.screens.accounts.AccountsScreen
import com.klmobile.passwordmanager.screens.accounts.AccountsViewModel
import com.klmobile.passwordmanager.screens.components.ComposableLifecycle
import com.klmobile.passwordmanager.screens.create.CreateAccountScreen
import com.klmobile.passwordmanager.screens.create.CreateAccountViewModel
import com.klmobile.passwordmanager.screens.login.LoginScreen
import com.klmobile.passwordmanager.screens.login.LoginViewModel
import com.klmobile.passwordmanager.screens.onboard.OnboardingScreen
import com.klmobile.passwordmanager.screens.onboard.OnboardingViewModel
import com.klmobile.passwordmanager.screens.register.RegisterScreen
import com.klmobile.passwordmanager.screens.register.RegisterViewModel
import com.klmobile.passwordmanager.utils.toast
import com.passwordmanager.domain.State
import com.passwordmanager.domain.entities.Account

object Screens {
    const val SCREEN_ONBOARDING = "onboarding"
    const val SCREEN_REGISTER = "register"
    const val SCREEN_LOGIN = "login"
    const val SCREEN_ACCOUNTS = "accounts"
    const val SCREEN_CREATE_ACCOUNTS = "create_account/{id}"


}

@Composable
fun AppNavigation(navHostController: NavHostController, mainViewModel: MainViewModel) {
    NavHost(navController = navHostController, startDestination = SCREEN_ONBOARDING) {
        composable(SCREEN_ONBOARDING) {
            val onboardingViewModel: OnboardingViewModel = hiltViewModel()
            val checkMasterPasswordExistResult =
                onboardingViewModel.checkMasterPasswordExistResult.collectAsState()

            OnboardingScreen(
                checkMasterPasswordExistResult = checkMasterPasswordExistResult.value,
                onGetLoginResult = {
                    if (it is State.DataState) {
                        val destinationRoute = if (it.data) {
                            SCREEN_LOGIN
                        } else {
                            SCREEN_REGISTER
                        }
                        navHostController.navigate(destinationRoute) {
                            popUpTo(navHostController.graph.id) {
                                inclusive = true
                            }
                        }
                    }
                })
        }

        composable(SCREEN_REGISTER) {
            val registerViewModel: RegisterViewModel = hiltViewModel()
            val updatePasswordState = registerViewModel.updateMasterPasswordResult.collectAsState()
            RegisterScreen(
                onRegistered = {
                    navHostController.navigate(SCREEN_LOGIN) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                },
                onPasswordChanged = { registerViewModel.onPassword(it) },
                onReentePasswordChanged = { registerViewModel.onReenterPassword(it) },
                onUpdatePassword = { registerViewModel.updatePassword() },
                onResetState = { registerViewModel.resetState() },
                updatePasswordState = updatePasswordState.value
            )
        }

        composable(SCREEN_LOGIN) {
            val loginViewModel: LoginViewModel = hiltViewModel()
            val loginState = loginViewModel.loginFlow.collectAsState()
            LoginScreen(loginState = loginState.value,
                onLoginPressed = { loginViewModel.login() },
                onDisposed = { loginViewModel.resetState() },
                onPasswordTyped = { loginViewModel.updatePassword(it) },
                onLoggedIn = {
                    navHostController.navigate(SCREEN_ACCOUNTS) {
                        popUpTo(navHostController.graph.id) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(SCREEN_ACCOUNTS) {
            val accountsViewModel: AccountsViewModel = hiltViewModel()
            val state = accountsViewModel.accountsState.collectAsState()
            ComposableLifecycle { _, event ->
                if (event == Lifecycle.Event.ON_RESUME) {
                    accountsViewModel.getAllAccounts()
                }
            }
            AccountsScreen(accountsState = state.value, onCreateAccountClicked = {
                navHostController.navigate(SCREEN_CREATE_ACCOUNTS)
            }, onAccountSelected = { account ->
                navHostController.navigate(
                    SCREEN_CREATE_ACCOUNTS.replace(
                        "{id}",
                        "${account.date}"
                    )
                )
            })
        }

        composable(SCREEN_CREATE_ACCOUNTS) {
            val context: Context = LocalContext.current
            val createAccountViewModel: CreateAccountViewModel = hiltViewModel()
            val createAccountState = createAccountViewModel.createAccountResult.collectAsState()
            val existAccountState = createAccountViewModel.getAccountResult.collectAsState()
            val id = it.arguments?.getString("id")?.toLongOrNull() ?: 0L

            LaunchedEffect(key1 = createAccountState.value, block = {
                when (val createAccountResult = createAccountState.value) {
                    is State.DataState -> {
                        if (id == null || id == 0L) {
                            context.toast(R.string.created)
                        } else {
                            context.toast(R.string.updated)
                        }
                        navHostController.popBackStack()
                    }

                    is State.ErrorState -> {
                        context.toast(createAccountResult.exception.message ?: "")
                    }

                    else -> {}
                }
            })

            ComposableLifecycle(onEvent = { _, event ->
                when (event) {
                    Lifecycle.Event.ON_CREATE -> {
                        if (id != 0L) {
                            createAccountViewModel.getAccount(id)
                        }

                    }
                    else -> {}
                }
            })

            if (id != 0L && existAccountState.value !is State.DataState) {
                CircularProgressIndicator()
            } else {
                CreateAccountScreen(
                    account = if (existAccountState.value is State.DataState) {
                        (existAccountState.value as State.DataState<Account>).data
                    } else {
                        null
                    },
                    onAccountInfoChanged = { accountField, value ->
                        createAccountViewModel.onAccountInformationChanged(accountField, value)
                    },
                    onCreateAccountPressed = {
                        createAccountViewModel.createAccount()
                    },
                    onClosePressed = {
                        navHostController.popBackStack()
                    }
                )
            }
        }
    }
}