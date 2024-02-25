package com.passwordmanager.data.pref

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreUtils @Inject constructor(
  @ApplicationContext
  private val context: Context
) {
  companion object {
    const val DATA_STORE_NAME = "PW_MANAGER"

    val KEY_MANAGER_PASSWORD = stringPreferencesKey("key_manager_password")
  }

  private val Context.dataStore by preferencesDataStore(
    name = DATA_STORE_NAME
  )

  suspend fun setPasswordManager(password: String) {
    context.dataStore.edit { preferences ->
      preferences[KEY_MANAGER_PASSWORD] = password
    }
  }

  fun getPasswordManager(): Flow<String> {
    return context.dataStore.data.map {
      it[KEY_MANAGER_PASSWORD] ?: ""
    }
  }
}