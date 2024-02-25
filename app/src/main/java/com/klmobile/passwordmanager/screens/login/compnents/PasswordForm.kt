
package com.klmobile.passwordmanager.screens.login.compnents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import com.klmobile.passwordmanager.R
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.klmobile.passwordmanager.ui.theme.HorizontalSpacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun PasswordForm(modifier: Modifier = Modifier,
                 hint: String? = null,
                 onTextChange: ((String) -> Unit)? = null) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.CenterVertically
  ) {
    var inputValue by remember {
      mutableStateOf("")
    }
    var passwordVisible by remember {
      mutableStateOf(false)
    }
    OutlinedTextField(
      modifier = Modifier
        .weight(1f),
      value = inputValue, onValueChange = {
        inputValue = it
        onTextChange?.invoke(it)
      }, label = {
        Text(text = hint ?: stringResource(id = R.string.password))
      }, visualTransformation = if (!passwordVisible) {
        PasswordVisualTransformation()
      } else {
        VisualTransformation.None
      }
    )
    HorizontalSpacing(spacing = 10.dp)
    Icon(
      modifier = Modifier.clickable {
        passwordVisible = !passwordVisible
      },
      imageVector = Icons.Outlined.Info, contentDescription = null
    )
  }
}