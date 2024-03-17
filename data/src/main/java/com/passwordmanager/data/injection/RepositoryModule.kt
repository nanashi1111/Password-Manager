package com.passwordmanager.data.injection

import com.passwordmanager.data.database.AccountDao
import com.passwordmanager.data.pref.DataStoreUtils
import com.passwordmanager.data.repositories.AccountRepository
import com.passwordmanager.data.repositories.AccountRepositoryImpl
import com.passwordmanager.data.repositories.MasterPasswordRepository
import com.passwordmanager.data.repositories.MasterPasswordRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
  @Provides
  @Singleton
  fun providePasswordManagerRepository(dataStoreUtils: DataStoreUtils): MasterPasswordRepository = MasterPasswordRepositoryImpl(dataStoreUtils)

  @Provides
  @Singleton
  fun provideAccountRepository(accountDao: AccountDao): AccountRepository = AccountRepositoryImpl(accountDao)
}