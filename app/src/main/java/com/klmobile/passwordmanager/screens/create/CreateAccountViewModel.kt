package com.klmobile.passwordmanager.screens.create

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.passwordmanager.domain.State
import com.passwordmanager.domain.entities.Account
import com.passwordmanager.domain.entities.AccountField
import com.passwordmanager.domain.usecase.CreateNewAccount
import com.passwordmanager.domain.usecase.GetAccountByCreatedDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class CreateAccountViewModel @Inject constructor(
  private val createNewAccount: CreateNewAccount,
  private val getAccountByCreatedDate: GetAccountByCreatedDate
) : ViewModel() {

  private val _createAccountParams = MutableStateFlow(
    CreateNewAccount.Params(
      id = Date().time,
      title = "",
      username = "",
      password = "",
      website = "",
      note = "",
      color = ""
    )
  )


  val createAccountParams = _createAccountParams.asStateFlow()

  private val _createAccountResult: MutableStateFlow<State<Account>> =
    MutableStateFlow(State.IdleState)
  val createAccountResult = _createAccountResult.asStateFlow()


  private val _getAccountResult: MutableStateFlow<State<Account>> =
    MutableStateFlow(State.IdleState)
  val getAccountResult = _getAccountResult.asStateFlow()


  fun onAccountInformationChanged(accountField: AccountField, value: String) {
    when (accountField) {
      AccountField.TITLE -> _createAccountParams.value = _createAccountParams.value.copy(title = value)
      AccountField.USERNAME -> _createAccountParams.value = _createAccountParams.value.copy(username = value)
      AccountField.PASSWORD -> _createAccountParams.value = _createAccountParams.value.copy(password = value)
      AccountField.WEBSITE -> _createAccountParams.value = _createAccountParams.value.copy(website = value)
      AccountField.NOTE -> _createAccountParams.value = _createAccountParams.value.copy(note = value)
      AccountField.COLOR -> _createAccountParams.value = _createAccountParams.value.copy(color = value)
    }
  }

  fun createAccount() {
    viewModelScope.launch {
      val id = if (_getAccountResult.value is State.DataState) {
        (_getAccountResult.value as State.DataState).data.date
      } else {
        Date().time
      }
      createNewAccount.invoke(
        createAccountParams.value.copy(id = id)
      ).collectLatest { _createAccountResult.emit(it) }
    }
  }

  fun getAccount(id: Long) {
    viewModelScope.launch {
      getAccountByCreatedDate.invoke(id).collectLatest {
        _getAccountResult.emit(it)
        if (it is State.DataState) {
          updateAccountFields(it.data)
        }
      }
    }
  }

  private fun updateAccountFields(account: Account) {
    account.run {
      _createAccountParams.value = _createAccountParams.value.copy(
        id = date,
        title = title,
        username = username,
        password = password,
        website = website,
        note = note,
        color = color
      )
    }
  }

}