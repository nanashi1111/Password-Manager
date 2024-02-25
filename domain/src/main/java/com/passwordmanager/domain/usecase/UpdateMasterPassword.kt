package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.PasswordManagerRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateMasterPassword @Inject constructor(private val passwordManagerRepository: PasswordManagerRepository) : UseCase<Boolean, UpdateMasterPassword.Params>() {

  data class Params(val password: String, val reenteredPassword: String)

  override fun buildFlow(param: Params): Flow<State<Boolean>> {
    return flow {
      if (param.password != param.reenteredPassword) {
        emit(State.ErrorState(PasswordNotMatchedException()))
      } else {
        passwordManagerRepository.updatePasswordManager(param.password)
        emit(State.DataState(true))
      }
    }
  }
}

class PasswordNotMatchedException : Throwable("Password not matched")