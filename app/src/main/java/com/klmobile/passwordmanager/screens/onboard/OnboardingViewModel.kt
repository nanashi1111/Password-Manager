package com.klmobile.passwordmanager.screens.onboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passwordmanager.domain.State
import com.passwordmanager.domain.usecase.CheckIfMasterPasswordSet
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(private val checkIfMasterPasswordSet: CheckIfMasterPasswordSet): ViewModel() {

  private val _checkMasterPasswordExistResult: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.IdleState)
  val checkMasterPasswordExistResult = _checkMasterPasswordExistResult.asStateFlow()

  init {
    viewModelScope.launch {
      checkIfMasterPasswordSet.invoke(Unit).collectLatest { _checkMasterPasswordExistResult.emit(it) }
    }
  }
}