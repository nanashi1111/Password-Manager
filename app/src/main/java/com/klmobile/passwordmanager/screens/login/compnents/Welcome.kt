package com.klmobile.passwordmanager.screens.login.compnents

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.klmobile.passwordmanager.R

@Composable
@Preview
fun Welcome(modifier: Modifier = Modifier) {
  Box (modifier = modifier) {
    Text(text = stringResource(id = R.string.welcome))
  }
}