package com.klmobile.passwordmanager.screens.create

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.klmobile.passwordmanager.screens.components.AppToolBar
import com.klmobile.passwordmanager.screens.create.components.AccountField
import com.klmobile.passwordmanager.ui.theme.PasswordManagerTheme
import com.klmobile.passwordmanager.utils.toColor
import com.klmobile.passwordmanager.utils.toHexString
import com.passwordmanager.domain.entities.Account
import com.passwordmanager.domain.entities.AccountField
import com.passwordmanager.domain.usecase.CreateNewAccount
import io.mhssn.colorpicker.ColorPickerDialog
import io.mhssn.colorpicker.ColorPickerType
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CreateAccountScreen(
  account: Account? = null,
  createAccountParam: CreateNewAccount.Params,
  onClosePressed: (() -> Unit)? = null,
  onAccountInfoChanged: ((AccountField, String) -> Unit)? = null,
  onCreateAccountPressed: (() -> Unit)? = null
) {
  LaunchedEffect(key1 = account, block = {
    Timber.d("account = $account")
  })

  var showColorPickerDialog by remember {
    mutableStateOf(false)
  }

  Scaffold(
    topBar = {
      AppToolBar(
        title = "",
        navigationIcon = Icons.Default.Close,
        actionIcons = listOf(
          Icons.Default.Done
        ),
        onActionsClicked = listOf {
          onCreateAccountPressed?.invoke()
        },
        onNavigationClicked = onClosePressed,
        color = if (createAccountParam.color.isNullOrEmpty()) {
          null
        } else {
          createAccountParam.color.toColor()
        }
      )
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 15.dp)
    ) {
      AccountField(
        accountField = AccountField.TITLE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.TITLE, value)
        },
        initialText = createAccountParam.title,
        actionItem = {
          val color = if (createAccountParam.color.isNullOrEmpty()) {
            MaterialTheme.colorScheme.primary
          } else {
            createAccountParam.color.toColor()
          }
          Box(
            modifier = Modifier
              .size(50.dp)
              .background(color, shape = CircleShape)
          ) {

          }
        },
        onActionItemClicked = {
          showColorPickerDialog = true
        }
      )
      AccountField(
        accountField = AccountField.USERNAME,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(accountField, value)
        },
        initialText = createAccountParam.username
      )
      AccountField(
        accountField = AccountField.PASSWORD,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(accountField, value)
        },
        initialText = createAccountParam.password
      )
      AccountField(
        accountField = AccountField.WEBSITE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(accountField, value)
        },
        initialText = createAccountParam.website
      )
      AccountField(
        accountField = AccountField.NOTE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(accountField, value)
        },
        initialText = createAccountParam.note
      )
    }
  }

  ColorPickerDialog(
    show = showColorPickerDialog,
    type = ColorPickerType.Classic(
      showAlphaBar = true
    ),
    properties = DialogProperties(),
    onDismissRequest = {
      showColorPickerDialog = false
    },
    onPickedColor = {
      showColorPickerDialog = false
      Timber.d("Picked: ${it}")
      onAccountInfoChanged?.invoke(AccountField.COLOR, it.toHexString())
    },
  )
}

@Preview
@Composable
fun CreateAccountScreenPreview() {
  PasswordManagerTheme {
    CreateAccountScreen(
      createAccountParam = CreateNewAccount.Params(
        0L, "FB", "DTV", "1111", "https://google.com", "Note", ""
      )
    )
  }
}