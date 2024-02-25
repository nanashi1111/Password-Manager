package com.klmobile.passwordmanager.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import com.klmobile.passwordmanager.R
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.klmobile.passwordmanager.MainViewModel
import com.klmobile.passwordmanager.screens.login.compnents.LoginActions
import com.klmobile.passwordmanager.screens.login.compnents.PasswordForm
import com.klmobile.passwordmanager.screens.login.compnents.Welcome
import com.klmobile.passwordmanager.ui.theme.VerticalSpacing
import com.klmobile.passwordmanager.utils.toast
import com.passwordmanager.domain.State

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LoginScreen(onLoggedIn: (() -> Unit)? = null) {
  val context = LocalContext.current
  val loginViewModel: LoginViewModel = hiltViewModel()
  val loginState = loginViewModel.loginFlow.collectAsState()
  DisposableEffect(key1 = loginState.value, effect = {

    val loginStateValue = loginState.value
    when (loginStateValue) {
      is State.DataState -> {
        if (loginStateValue.data) {
          onLoggedIn?.invoke()
        } else {
          context.toast(context.getString(R.string.error_password_not_correct))
        }
      }

      is State.ErrorState -> {
        context.toast(loginStateValue.exception.message ?: "")
      }

      else -> {}
    }

    onDispose {
      loginViewModel.resetState()
    }
  })

  Scaffold(topBar = {
    TopAppBar(
      title = { Text(text = stringResource(id = R.string.app_name), style = TextStyle(color = MaterialTheme.colorScheme.onPrimary)) },
      colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary
      )
    )
  }) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .background(MaterialTheme.colorScheme.background),
    ) {
      Welcome(modifier = Modifier.padding(20.dp))
      VerticalSpacing(spacing = 10.dp)
      PasswordForm(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
        onTextChange = {
          loginViewModel.updatePassword(it)
        }
      )
      VerticalSpacing(spacing = 10.dp)
      LoginActions(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
        onLoginPressed = {
          loginViewModel.login()
        }
      )
    }
  }
}