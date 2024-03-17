package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.MasterPasswordRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

abstract class UpdateMasterPassword : UseCase<Boolean, UpdateMasterPassword.Params>() {
  data class Params(val password: String, val reenteredPassword: String)
}

class UpdateMasterPasswordImpl constructor(private val masterPasswordRepository: MasterPasswordRepository) : UpdateMasterPassword() {

  override fun buildFlow(param: UpdateMasterPassword.Params): Flow<State<Boolean>> {
    return flow {
      if (param.password != param.reenteredPassword) {
        emit(State.ErrorState(PasswordNotMatchedException()))
      } else {
        masterPasswordRepository.updatePasswordManager(param.password)
        emit(State.DataState(true))
      }
    }
  }
}

class PasswordNotMatchedException : Throwable("Password not matched")