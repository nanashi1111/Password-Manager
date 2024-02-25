package com.klmobile.passwordmanager.screens.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passwordmanager.domain.State
import com.passwordmanager.domain.usecase.CheckIfMasterPasswordSet
import com.passwordmanager.domain.usecase.UpdateMasterPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
  private val updateMasterPassword: UpdateMasterPassword,
  private val checkIfMasterPasswordSet: CheckIfMasterPasswordSet
) : ViewModel() {

  private var passwordParams = UpdateMasterPassword.Params("", "")
  private val _updateMasterPasswordResult: MutableStateFlow<State<Boolean>> = MutableStateFlow(State.IdleState)
  val updateMasterPasswordResult = _updateMasterPasswordResult.asStateFlow()



  fun onPassword(password: String) {
    passwordParams = passwordParams.copy(password = password)
  }

  fun onReenterPassword(password: String) {
    passwordParams = passwordParams.copy(reenteredPassword = password)
  }

  fun updatePassword() {
    viewModelScope.launch {
      updateMasterPassword.invoke(passwordParams).collectLatest {
        _updateMasterPasswordResult.emit(it)
      }
    }
  }

  fun resetState() = viewModelScope.launch {
    _updateMasterPasswordResult.emit(State.IdleState)
  }
}