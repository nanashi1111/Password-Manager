package com.klmobile.passwordmanager.screens.accounts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.klmobile.passwordmanager.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountsScreen() {
  Scaffold(topBar = {
    TopAppBar(
      title = {
        Text(text = stringResource(id = R.string.accounts), style = TextStyle(color = MaterialTheme.colorScheme.onPrimary))
      }, colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = MaterialTheme.colorScheme.primary
      )
    )
  }) {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .background(MaterialTheme.colorScheme.background)
    ) {

    }
  }
}