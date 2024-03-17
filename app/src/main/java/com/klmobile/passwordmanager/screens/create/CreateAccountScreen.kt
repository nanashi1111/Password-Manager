package com.klmobile.passwordmanager.screens.create

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.screens.components.AppToolBar
import com.klmobile.passwordmanager.screens.create.components.AccountTitle
import com.klmobile.passwordmanager.ui.theme.PasswordManagerTheme
import com.passwordmanager.domain.entities.AccountField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
  onClosePressed: (() -> Unit)? = null,
  onAccountInfoChanged: ((AccountField, String) -> Unit)? = null,
  onCreateAccountPressed: (() -> Unit)? = null
) {
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
        onNavigationClicked = onClosePressed
      )
    }
  ) {
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 15.dp)
    ) {
      AccountTitle(
        accountField = AccountField.TITLE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.TITLE, value)
        }

      )
      AccountTitle(
        accountField = AccountField.ACCOUNT,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.ACCOUNT, value)
        }
      )
      AccountTitle(
        accountField = AccountField.USERNAME,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.USERNAME, value)
        }
      )
      AccountTitle(
        accountField = AccountField.PASSWORD,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.PASSWORD, value)
        }
      )
      AccountTitle(
        accountField = AccountField.WEBSITE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.WEBSITE, value)
        }
      )
      AccountTitle(
        accountField = AccountField.NOTE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.NOTE, value)
        }
      )
    }
  }
}

@Preview
@Composable
fun CreateAccountScreenPreview() {
  PasswordManagerTheme {
    CreateAccountScreen()
  }
}