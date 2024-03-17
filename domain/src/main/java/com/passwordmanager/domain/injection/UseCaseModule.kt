package com.passwordmanager.domain.injection

import com.passwordmanager.data.repositories.AccountRepository
import com.passwordmanager.data.repositories.MasterPasswordRepository
import com.passwordmanager.domain.usecase.CheckIfMasterPasswordSet
import com.passwordmanager.domain.usecase.CheckIfMasterPasswordSetImpl
import com.passwordmanager.domain.usecase.CreateNewAccount
import com.passwordmanager.domain.usecase.CreateNewAccountImpl
import com.passwordmanager.domain.usecase.GetAllAccounts
import com.passwordmanager.domain.usecase.GetAllAccountsImpl
import com.passwordmanager.domain.usecase.Login
import com.passwordmanager.domain.usecase.LoginImpl
import com.passwordmanager.domain.usecase.UpdateMasterPassword
import com.passwordmanager.domain.usecase.UpdateMasterPasswordImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {
  @Provides
  @Singleton
  fun provideCreateAccountUseCase(accountRepository: AccountRepository): CreateNewAccount = CreateNewAccountImpl(accountRepository)

  @Provides
  @Singleton
  fun provideGetAllAccountsUseCase(accountRepository: AccountRepository): GetAllAccounts = GetAllAccountsImpl(accountRepository)

  @Singleton
  @Provides
  fun provideCheckIfMasterPasswordSetUseCase(masterPasswordRepository: MasterPasswordRepository): CheckIfMasterPasswordSet = CheckIfMasterPasswordSetImpl(masterPasswordRepository)

  @Singleton
  @Provides
  fun provideLoginUseCase(masterPasswordRepository: MasterPasswordRepository): Login = LoginImpl(masterPasswordRepository)

  @Singleton
  @Provides
  fun provideUpdateMasterPasswordUseCase(masterPasswordRepository: MasterPasswordRepository): UpdateMasterPassword = UpdateMasterPasswordImpl(masterPasswordRepository)
}