package com.klmobile.passwordmanager.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.screens.components.AppToolBar
import com.klmobile.passwordmanager.screens.create.components.AccountField
import com.klmobile.passwordmanager.ui.theme.PasswordManagerTheme
import com.passwordmanager.domain.entities.Account
import com.passwordmanager.domain.entities.AccountField
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccountScreen(
  account: Account? = null,
  onClosePressed: (() -> Unit)? = null,
  onAccountInfoChanged: ((AccountField, String) -> Unit)? = null,
  onCreateAccountPressed: (() -> Unit)? = null
) {
  LaunchedEffect(key1 = account, block = {
    Timber.d("account = $account")
  })
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
      AccountField(
        accountField = AccountField.TITLE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.TITLE, value)
        },
        initialText = account?.title ?: ""
      )
      AccountField(
        accountField = AccountField.USERNAME,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.USERNAME, value)
        },
        initialText = account?.username ?: ""
      )
      AccountField(
        accountField = AccountField.PASSWORD,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.PASSWORD, value)
        },
        initialText = account?.password ?: ""
      )
      AccountField(
        accountField = AccountField.WEBSITE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.WEBSITE, value)
        },
        initialText = account?.website ?: ""
      )
      AccountField(
        accountField = AccountField.NOTE,
        onChanged = { value, accountField ->
          onAccountInfoChanged?.invoke(AccountField.NOTE, value)
        },
        initialText = account?.note ?: ""
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