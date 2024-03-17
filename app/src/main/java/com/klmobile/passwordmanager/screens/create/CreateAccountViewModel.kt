package com.klmobile.passwordmanager.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passwordmanager.domain.State
import com.passwordmanager.domain.entities.Account
import com.passwordmanager.domain.entities.AccountField
import com.passwordmanager.domain.usecase.CreateNewAccount
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
  private val createNewAccount: CreateNewAccount
) : ViewModel() {

  private var title = ""
  private var account = ""
  private var username = ""
  private var password = ""
  private var website = ""
  private var note = ""

  private val _createAccountResult: MutableStateFlow<State<Account>> = MutableStateFlow(State.IdleState)
  val createAccountResult = _createAccountResult.asStateFlow()

  fun onAccountInformationChanged(accountField: AccountField, value: String) {
    when (accountField) {
      AccountField.TITLE -> title = value
      AccountField.ACCOUNT -> account = value
      AccountField.USERNAME -> username = value
      AccountField.PASSWORD -> password = value
      AccountField.WEBSITE -> website = value
      AccountField.NOTE -> note = value
    }
  }

  fun createAccount() {
    viewModelScope.launch {
      createNewAccount.invoke(
        CreateNewAccount.Params(
          title, account, username, password, website, note
        )
      ).collectLatest { _createAccountResult.emit(it) }

    }
  }

}