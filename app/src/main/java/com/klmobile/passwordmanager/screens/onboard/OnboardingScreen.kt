package com.klmobile.passwordmanager.screens.onboard

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.klmobile.passwordmanager.Screens
import com.passwordmanager.domain.State

@Composable
fun OnboardingScreen(navHostController: NavHostController) {
  val viewModel: OnboardingViewModel = hiltViewModel()
  val checkMasterPasswordExistResult = viewModel.checkMasterPasswordExistResult.collectAsState()
  Surface {
    LaunchedEffect(key1 = checkMasterPasswordExistResult.value, block = {
      val result = checkMasterPasswordExistResult.value
      if (result is State.DataState) {
        val destinationRoute = if (result.data) {
          Screens.SCREEN_LOGIN
        } else {
          Screens.SCREEN_REGISTER
        }
        navHostController.navigate(destinationRoute) {
          popUpTo(navHostController.graph.id) {
            inclusive = true
          }
        }
      }
    })
  }
}