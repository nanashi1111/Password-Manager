package com.passwordmanager.domain.usecase

import com.passwordmanager.data.entities.AccountDTO
import com.passwordmanager.data.repositories.AccountRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import com.passwordmanager.domain.entities.Account
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Date

abstract class CreateNewAccount : UseCase<Account, CreateNewAccount.Params>() {
    data class Params(
        val id: Long,
        val title: String,
        val username: String,
        val password: String,
        val website: String,
        val note: String,
        val color: String,
    )
}

class CreateNewAccountImpl(private val accountRepository: AccountRepository) : CreateNewAccount() {
    override fun buildFlow(param: Params): Flow<State<Account>> {
        return flow {
            if (param.title.isEmpty()) {
                emit(State.ErrorState(MissingTitleException()))
                return@flow
            }
            if (param.username.isEmpty()) {
                emit(State.ErrorState(MissingUsernameException()))
                return@flow
            }
            if (param.password.isEmpty()) {
                emit(State.ErrorState(MissingPasswordException()))
                return@flow
            }
            val accountDTO = AccountDTO(
                date = param.id,
                title = param.title,
                username = param.username,
                password = param.password,
                website = param.website,
                note = param.note,
                color = param.color
            )
            accountRepository.createNewAccount(accountDTO)
            accountRepository.getAccountByCreatedDate(accountDTO.date)?.let {
                emit(State.DataState(Account.fromDto(it)))
            } ?: kotlin.run {
                emit(State.ErrorState(CreateAccountException))
            }
        }
    }
}

object CreateAccountException : Throwable("Can't create account")
class MissingTitleException : Throwable("Missing title!")
class MissingUsernameException : Throwable("Missing username!")
class MissingPasswordException : Throwable("Missing password!")