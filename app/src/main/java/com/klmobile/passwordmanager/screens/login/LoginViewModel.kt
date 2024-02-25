package com.klmobile.passwordmanager.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passwordmanager.domain.State
import com.passwordmanager.domain.usecase.Login
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val login: Login) : ViewModel() {
  var typedPassword = ""
    private set

  private val _loginFlow = MutableStateFlow<State<Boolean>>(State.IdleState)
  val loginFlow = _loginFlow.asStateFlow()

  fun updatePassword(password: String) {
    viewModelScope.launch(Dispatchers.Default) {
      typedPassword = password
    }
  }

  fun login() {
    viewModelScope.launch {
      login.invoke(typedPassword).collectLatest {
        _loginFlow.emit(it)
      }
    }
  }

  fun resetState() = viewModelScope.launch { _loginFlow.value = State.IdleState }


}