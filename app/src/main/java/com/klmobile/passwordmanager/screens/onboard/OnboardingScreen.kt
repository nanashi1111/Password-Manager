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
fun OnboardingScreen(
  checkMasterPasswordExistResult: State<Boolean>,
  onGetLoginResult: ((State<Boolean>) -> Unit)? = null) {
  Surface {
    LaunchedEffect(key1 = checkMasterPasswordExistResult, block = {
      val result = checkMasterPasswordExistResult

      onGetLoginResult?.invoke(result)
    })
  }
}