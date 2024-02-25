package com.passwordmanager.data.repositories

import com.passwordmanager.data.pref.DataStoreUtils
import kotlinx.coroutines.flow.Flow

interface PasswordManagerRepository {
  suspend fun updatePasswordManager(password: String)
  fun getPasswordManager(): Flow<String>
}

class PasswordManagerRepositoryImpl(
  private val dataStoreUtils: DataStoreUtils
) : PasswordManagerRepository {
  override suspend fun updatePasswordManager(password: String) = dataStoreUtils.setPasswordManager(password)

  override fun getPasswordManager(): Flow<String> = dataStoreUtils.getPasswordManager()

}