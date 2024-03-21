package com.klmobile.passwordmanager.screens.create.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.R
import com.klmobile.passwordmanager.ui.theme.HorizontalSpacing
import com.klmobile.passwordmanager.ui.theme.VerticalSpacing
import com.passwordmanager.domain.entities.AccountField
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountField(
  modifier: Modifier = Modifier,
  initialText: String = "",
  accountField: AccountField = AccountField.TITLE,
  onChanged: ((String, AccountField) -> Unit)? = null,

  actionItem: @Composable (() -> Unit)? = null,
  onActionItemClicked: (() -> Unit)? = null
) {
//  var text by remember {
//    mutableStateOf(initialText)
//  }
  LaunchedEffect(key1 = initialText, block = {
    Timber.d("AccountTitle: type = ${accountField} ; initText = $initialText")
  })
  Column(modifier = modifier) {
    VerticalSpacing(spacing = 15.dp)
    Row(modifier = Modifier.fillMaxWidth()) {
      OutlinedTextField(
        modifier = Modifier.weight(1f),
        value = initialText, onValueChange = {
          onChanged?.invoke(it, accountField)
        },
        visualTransformation = if (accountField == AccountField.PASSWORD) {
          PasswordVisualTransformation()
        } else {
          VisualTransformation.None
        },
        placeholder = {
          Text(
            text = when (accountField) {
              AccountField.NOTE -> stringResource(id = R.string.note)
              AccountField.PASSWORD -> stringResource(id = R.string.password)
              AccountField.TITLE -> stringResource(id = R.string.title)
              AccountField.WEBSITE -> stringResource(id = R.string.website)
              AccountField.USERNAME -> stringResource(id = R.string.username)
              AccountField.COLOR -> stringResource(id = R.string.color)
            }
          )
        })
      actionItem?.let {
        HorizontalSpacing(spacing = 15.dp)
        Box(modifier = Modifier
          .wrapContentSize()
          .clickable { onActionItemClicked?.invoke() }) {
          it()
        }
      }
    }


  }

}