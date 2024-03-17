package com.passwordmanager.data.repositories

import com.passwordmanager.data.database.AccountDao
import com.passwordmanager.data.entities.AccountDTO

interface AccountRepository {
  suspend fun getAllAccount(): List<AccountDTO>
  suspend fun createNewAccount(accountDTO: AccountDTO)

  suspend fun getAccountByCreatedDate(date: Long): AccountDTO?
}

class AccountRepositoryImpl constructor(private val accountDao: AccountDao): AccountRepository {
  override suspend fun getAllAccount(): List<AccountDTO> = accountDao.getAllAccount()
  override suspend fun createNewAccount(accountDTO: AccountDTO) = accountDao.createNewAccount(accountDTO)

  override suspend fun getAccountByCreatedDate(date: Long): AccountDTO? = accountDao.getAccount(date)

}