package com.klmobile.passwordmanager.screens.login.compnents

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.klmobile.passwordmanager.R

@Composable
@Preview
fun LoginActions(
  modifier: Modifier = Modifier,
  onLoginPressed: (() -> Unit)? = null
) {

  ElevatedButton(
    modifier = modifier,
    onClick = { onLoginPressed?.invoke() },
    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
  ) {
    Text(text = stringResource(id = R.string.enter))
  }
}