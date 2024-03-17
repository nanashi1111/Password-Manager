package com.passwordmanager.domain.usecase

import com.passwordmanager.data.entities.AccountDTO
import com.passwordmanager.data.repositories.AccountRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import com.passwordmanager.domain.entities.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

abstract class CreateNewAccount : UseCase<Account, CreateNewAccount.Params>() {
  class Params(
    val title: String, val account: String, val username: String, val password: String, val website: String, val note: String
  )
}

class CreateNewAccountImpl(private val accountRepository: AccountRepository) : CreateNewAccount() {
  override fun buildFlow(param: Params): Flow<State<Account>> {
    return flow {
      val accountDTO = AccountDTO(
        date = Date().time,
        title = param.title,
        account = param.account,
        username = param.username,
        password = param.password,
        website = param.website,
        note = param.note
      )
      accountRepository.createNewAccount(accountDTO)
      accountRepository.getAccountByCreatedDate(accountDTO.date)?.let {
        emit(State.DataState(Account.fromDto(it)))
      } ?: kotlin.run {
        emit(State.ErrorState(CreateAccountException))
      }
    }
  }
}

object CreateAccountException : Throwable("Can't create account")