package com.klmobile.passwordmanager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import com.klmobile.passwordmanager.ui.theme.PasswordManagerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val mainViewModel: MainViewModel by viewModels()
    setContent {
      val darkTheme = mainViewModel.darkTheme.collectAsState()
      PasswordManagerTheme (darkTheme = darkTheme.value) {
        val navHostController = rememberNavController()
        AppNavigation(navHostController = navHostController, mainViewModel = mainViewModel)
      }
    }
  }
}
