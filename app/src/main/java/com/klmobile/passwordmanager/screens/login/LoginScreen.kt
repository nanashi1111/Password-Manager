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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.screens.login.compnents.LoginActions
import com.klmobile.passwordmanager.screens.login.compnents.PasswordForm
import com.klmobile.passwordmanager.screens.login.compnents.Welcome
import com.klmobile.passwordmanager.ui.theme.VerticalSpacing
import com.klmobile.passwordmanager.utils.toast
import com.passwordmanager.domain.State

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
  onLoginPressed: (() -> Unit)? = null,
  onPasswordTyped: ((String) -> Unit)? = null,
  onDisposed: (() -> Unit)? = null,
  onLoggedIn: (() -> Unit)? = null,
  loginState: State<Boolean>
) {
  val context = LocalContext.current
  DisposableEffect(key1 = loginState, effect = {
    when (loginState) {
      is State.DataState -> {
        if (loginState.data) {
          onLoggedIn?.invoke()
        } else {
          context.toast(context.getString(R.string.error_password_not_correct))
        }
      }

      is State.ErrorState -> {
        context.toast(loginState.exception.message ?: "")
      }

      else -> {}
    }

    onDispose {
      onDisposed?.invoke()
      //loginViewModel.resetState()
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
          //loginViewModel.updatePassword(it)
          onPasswordTyped?.invoke(it)
        }
      )
      VerticalSpacing(spacing = 10.dp)
      LoginActions(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
        onLoginPressed = {
          onLoginPressed?.invoke()
          //loginViewModel.login()
        }
      )
    }
  }
}