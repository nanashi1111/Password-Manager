package com.passwordmanager.data.repositories

import com.passwordmanager.data.pref.DataStoreUtils
import kotlinx.coroutines.flow.Flow

interface MasterPasswordRepository {
  suspend fun updatePasswordManager(password: String)
  fun getPasswordManager(): Flow<String>
}

class MasterPasswordRepositoryImpl(
  private val dataStoreUtils: DataStoreUtils
) : MasterPasswordRepository {
  override suspend fun updatePasswordManager(password: String) = dataStoreUtils.setPasswordManager(password)

  override fun getPasswordManager(): Flow<String> = dataStoreUtils.getPasswordManager()

}