package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.PasswordManagerRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Login @Inject constructor(private val passwordManagerRepository: PasswordManagerRepository) : UseCase<Boolean, String>() {
  override fun buildFlow(param: String): Flow<State<Boolean>> {
    return passwordManagerRepository.getPasswordManager().map {
      if (it == param) {
        State.DataState(true)
      } else {
        State.ErrorState(LoginException())
      }
    }
  }
}

class LoginException: Throwable("Password is not correct")