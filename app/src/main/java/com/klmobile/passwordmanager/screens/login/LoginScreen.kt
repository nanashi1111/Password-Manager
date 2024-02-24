package com.klmobile.passwordmanager.screens.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun LoginScreen() {
  Scaffold(topBar = {
    TopAppBar(title = { Text(text = "Password manager", style = TextStyle(color = Color.White)) },
      colors = TopAppBarDefaults.smallTopAppBarColors(
        containerColor = Color.Blue
      ))
  }) {
    it.calculateTopPadding()
    Column (modifier = Modifier
      .fillMaxSize()
      .padding(it), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
      Text(text = "OK")
    }
  }
}