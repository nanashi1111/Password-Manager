package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.PasswordManagerRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CheckIfMasterPasswordSet @Inject constructor(private val passwordManagerRepository: PasswordManagerRepository) : UseCase<Boolean, Unit>() {
  override fun buildFlow(param: Unit): Flow<State<Boolean>> {
    return passwordManagerRepository.getPasswordManager().map {
      State.DataState(it.isNotEmpty())
    }
  }
}