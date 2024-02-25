package com.klmobile.passwordmanager

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(): ViewModel() {

  private val _darkTheme = MutableStateFlow(false)
  val darkTheme = _darkTheme.asStateFlow()

  fun toggleTheme() {
    _darkTheme.value = !darkTheme.value
  }
}