package com.klmobile.passwordmanager.screens.accounts.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun CreateAccount(
  modifier: Modifier = Modifier,
  onClicked: (() -> Unit)? = null) {
  FloatingActionButton(
    modifier = modifier,
    containerColor = MaterialTheme.colorScheme.primaryContainer,
    onClick = { onClicked?.invoke() }) {
      Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = MaterialTheme.colorScheme.onPrimaryContainer)
  }
}