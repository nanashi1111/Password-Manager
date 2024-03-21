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

    private var title = ""
    private var username = ""
    private var password = ""
    private var website = ""
    private var note = ""

    val _existAccountId = MutableStateFlow(0L)
    val existAccountId = _existAccountId.asStateFlow()

    private val _createAccountResult: MutableStateFlow<State<Account>> =
        MutableStateFlow(State.IdleState)
    val createAccountResult = _createAccountResult.asStateFlow()


    private val _getAccountResult: MutableStateFlow<State<Account>> =
        MutableStateFlow(State.IdleState)
    val getAccountResult = _getAccountResult.asStateFlow()

    fun setExistAccountId(id: Long) {
        _existAccountId.value = id
    }

    fun onAccountInformationChanged(accountField: AccountField, value: String) {
        when (accountField) {
            AccountField.TITLE -> title = value
            AccountField.USERNAME -> username = value
            AccountField.PASSWORD -> password = value
            AccountField.WEBSITE -> website = value
            AccountField.NOTE -> note = value
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
                CreateNewAccount.Params(
                    id, title, username, password, website, note
                )
            ).collectLatest { _createAccountResult.emit(it) }

        }
    }

    fun getAccount(/*id: Long*/) {
        val id = _existAccountId.value
        if (id == 0L) {
            return
        }
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
        account.let {
            title = it.title
            username = it.username
            password = it.password
            website = it.website
            note = it.note
        }
    }

}