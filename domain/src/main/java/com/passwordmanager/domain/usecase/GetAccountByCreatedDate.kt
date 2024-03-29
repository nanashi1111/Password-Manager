/**
 * @file GetAccountByCreatedDate.kt
 * @author Duong Tuan Vu
 *
 * © 2024 Hyundai Motor Company. All Rights Reserved.
 *
 * This software is copyright protected and proprietary to Hyundai Motor Company.
 * Do not copy without prior permission. Any copy of this software or of any
 * derivative work must include the above copyright notice, this paragraph and
 * the one after it.
 *
 * This software is made available on an "AS IS" condition, and Hyundai Motor Company
 * disclaims all warranties of any kind, whether express or implied, statutory or
 * otherwise, including without limitation any warranties of merchantability or
 * fitness for a particular purpose, absence of errors, accuracy, completeness of
 * results or the validity, scope, or non-infringement of any intellectual property.
 */
package com.passwordmanager.domain.usecase

import com.passwordmanager.data.repositories.AccountRepository
import com.passwordmanager.domain.State
import com.passwordmanager.domain.UseCase
import com.passwordmanager.domain.entities.Account
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class GetAccountByCreatedDate : UseCase<Account, Long>()

class GetAccountByCreatedDateImpl(private val accountRepository: AccountRepository) :
    GetAccountByCreatedDate() {
    override fun buildFlow(param: Long): Flow<State<Account>> {
        return flow {
            accountRepository.getAccountByCreatedDate(param)?.let {
                emit(State.DataState(Account.fromDto(it)))
            } ?: run {
                emit(State.ErrorState(AccountNotFoundException(param)))
            }
        }
    }
}

class AccountNotFoundException(val id: Long) : Throwable("Not found account $id")