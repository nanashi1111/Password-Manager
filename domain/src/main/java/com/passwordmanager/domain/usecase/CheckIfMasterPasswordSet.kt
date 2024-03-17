package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.MasterPasswordRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

abstract class CheckIfMasterPasswordSet : UseCase<Boolean, Unit>()

class CheckIfMasterPasswordSetImpl constructor(private val masterPasswordRepository: MasterPasswordRepository) : CheckIfMasterPasswordSet() {
  override fun buildFlow(param: Unit): Flow<State<Boolean>> {
    return masterPasswordRepository.getPasswordManager().map {
      State.DataState(it.isNotEmpty())
    }
  }
}