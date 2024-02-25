package com.passwordmanager.domain.usecase

import com.passwordmanager.data.pref.DataStoreUtils
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckPasswordManager @Inject constructor(private val dataStoreUtils: DataStoreUtils) : UseCase<Boolean, String>() {
  override fun buildFlow(param: String) = dataStoreUtils.getPasswordManager().map {
    if (it == param) {
      State.DataState(true)
    } else {
      State.ErrorState(PasswordManagerError())
    }
  }
}

class PasswordManagerError() : Throwable("Password is not correct")