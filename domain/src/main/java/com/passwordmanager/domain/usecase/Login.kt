package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.MasterPasswordRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

abstract class Login : UseCase<Boolean, String>()
class LoginImpl constructor(private val masterPasswordRepository: MasterPasswordRepository) : Login() {
  override fun buildFlow(param: String): Flow<State<Boolean>> {
    return masterPasswordRepository.getPasswordManager().map {
      if (it == param) {
        State.DataState(true)
      } else {
        State.ErrorState(LoginException())
      }
    }
  }
}

class LoginException : Throwable("Password is not correct")