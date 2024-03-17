package com.klmobile.passwordmanager.screens.register

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.klmobile.passwordmanager.R
import com.klmobile.passwordmanager.screens.components.AppToolBar
import com.klmobile.passwordmanager.screens.login.compnents.LoginActions
import com.klmobile.passwordmanager.screens.login.compnents.PasswordForm
import com.klmobile.passwordmanager.ui.theme.VerticalSpacing
import com.klmobile.passwordmanager.utils.toast
import com.passwordmanager.domain.State
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun RegisterScreen(
  onRegistered: (() -> Unit)? = null,
  onPasswordChanged: ((String) -> Unit)?=null,
  onReentePasswordChanged: ((String) -> Unit)?=null,
  onUpdatePassword: (() -> Unit)? = null,
  onResetState: (() -> Unit)? = null,
  updatePasswordState: State<Boolean> = State.IdleState
) {
//  val viewModel: RegisterViewModel = hiltViewModel()
//  val updatePasswordState = viewModel.updateMasterPasswordResult.collectAsState()
  val context = LocalContext.current
  Scaffold(
    topBar = {
      AppToolBar(title = stringResource(id = R.string.app_name))
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .background(MaterialTheme.colorScheme.background),
      verticalArrangement = Arrangement.Top
    ) {
      Text(
        modifier = Modifier.padding(20.dp),
        text = stringResource(id = R.string.set_your_master_password)
      )
      VerticalSpacing(spacing = 10.dp)
      PasswordForm(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        hint = stringResource(id = R.string.your_password),
        onTextChange = {
          onPasswordChanged?.invoke(it)

        }
      )
      VerticalSpacing(spacing = 10.dp)
      PasswordForm(
        modifier = Modifier
          .fillMaxWidth()
          .padding(20.dp),
        hint = stringResource(id = R.string.reenter_your_password),
        onTextChange = {
          onReentePasswordChanged?.invoke(it)
        }
      )
      VerticalSpacing(spacing = 10.dp)
      LoginActions(
        modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 20.dp),
        onLoginPressed = {
          onUpdatePassword?.invoke()
        }
      )
    }
  }

  if (updatePasswordState is State.LoadingState) {
    Dialog(
      onDismissRequest = {
        Timber.tag("Register").d("onDismissRequest")
      },
      properties = DialogProperties(
        dismissOnBackPress = true,
        dismissOnClickOutside = true
      )
    ) {
      CircularProgressIndicator(color = MaterialTheme.colorScheme.outline)
    }
  }

  DisposableEffect(key1 = updatePasswordState, effect = {
    when (updatePasswordState) {
      is State.DataState -> {
        onRegistered?.invoke()
      }

      is State.ErrorState -> {
        context.toast((updatePasswordState as State.ErrorState).exception.message ?: "")
      }

      else -> {}
    }

    onDispose {
      onResetState?.invoke()
      //viewModel.resetState()
    }
  })

}