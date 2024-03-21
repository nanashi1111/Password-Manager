package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.AccountRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import com.passwordmanager.domain.entities.Account
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class GetAllAccounts : UseCase<List<Account>, Unit>() {
}

class GetAllAccountsImpl constructor(private val accountRepository: AccountRepository) : GetAllAccounts() {
  override fun buildFlow(param: Unit): Flow<State<List<Account>>> {
    return flow {
      val account = accountRepository.getAllAccount().map { Account.fromDto(it) }
      emit(State.DataState(account))
    }
  }

}