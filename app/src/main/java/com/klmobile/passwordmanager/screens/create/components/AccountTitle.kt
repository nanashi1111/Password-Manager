package com.klmobile.passwordmanager.screens.create.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.ui.theme.VerticalSpacing
import com.passwordmanager.domain.entities.AccountField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountTitle(
  modifier: Modifier = Modifier,
  accountField: AccountField = AccountField.ACCOUNT,
  onChanged: ((String, AccountField) -> Unit)? = null,
) {
  var text by remember {
    mutableStateOf("")
  }
  Column(modifier = modifier) {
    VerticalSpacing(spacing = 15.dp)
    OutlinedTextField(
      modifier = Modifier.fillMaxWidth(),
      value = text, onValueChange = {
        text = it
        onChanged?.invoke(it, accountField)
      },
      placeholder = {
        Text(
          text = when (accountField) {
            AccountField.ACCOUNT -> "Account"
            AccountField.NOTE -> "Note"
            AccountField.PASSWORD -> "Password"
            AccountField.TITLE -> "Title"
            AccountField.WEBSITE -> "Website"
            AccountField.USERNAME -> "Username"
          }
        )
      })

  }

}