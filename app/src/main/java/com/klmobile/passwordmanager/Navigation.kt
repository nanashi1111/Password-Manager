package com.klmobile.passwordmanager

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.klmobile.passwordmanager.Screens.SCREEN_ACCOUNTS
import com.klmobile.passwordmanager.Screens.SCREEN_LOGIN
import com.klmobile.passwordmanager.Screens.SCREEN_ONBOARDING
import com.klmobile.passwordmanager.Screens.SCREEN_REGISTER
import com.klmobile.passwordmanager.screens.accounts.AccountsScreen
import com.klmobile.passwordmanager.screens.login.LoginScreen
import com.klmobile.passwordmanager.screens.onboard.OnboardingScreen
import com.klmobile.passwordmanager.screens.register.RegisterScreen
import com.klmobile.passwordmanager.screens.register.RegisterViewModel

object Screens {
  const val SCREEN_ONBOARDING = "onboarding"
  const val SCREEN_REGISTER = "register"
  const val SCREEN_LOGIN = "login"
  const val SCREEN_ACCOUNTS = "accounts"

}

@Composable
fun AppNavigation(navHostController: NavHostController, mainViewModel: MainViewModel) {
  NavHost(navController = navHostController, startDestination = SCREEN_ONBOARDING) {
    composable(SCREEN_ONBOARDING) {
      OnboardingScreen(navHostController = navHostController)
    }
    composable(SCREEN_REGISTER) {
      RegisterScreen {
        navHostController.navigate(SCREEN_LOGIN) {
          popUpTo(navHostController.graph.id) {
            inclusive = true
          }
        }

      }
    }
    composable(SCREEN_LOGIN) {
      LoginScreen {
        navHostController.navigate(SCREEN_ACCOUNTS) {
          popUpTo(navHostController.graph.id) {
            inclusive = true
          }
        }
      }
    }
    composable(SCREEN_ACCOUNTS) {
      AccountsScreen()
    }
  }
}