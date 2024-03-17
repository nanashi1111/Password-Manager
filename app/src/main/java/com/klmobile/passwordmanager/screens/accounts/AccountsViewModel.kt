package com.klmobile.passwordmanager.screens.accounts

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passwordmanager.domain.State
import com.passwordmanager.domain.entities.Account
import com.passwordmanager.domain.usecase.GetAllAccounts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
  private val getAllAccounts: GetAllAccounts
) : ViewModel() {

  private val _accountsState: MutableStateFlow<State<List<Account>>> = MutableStateFlow(State.IdleState)
  val accountsState = _accountsState.asStateFlow()

  fun getAllAccounts() {
    viewModelScope.launch {
      Timber.tag("AccountsViewModel").d("Getting all accounts")
      getAllAccounts.invoke(Unit).collectLatest { _accountsState.emit(it) }
    }
  }
}