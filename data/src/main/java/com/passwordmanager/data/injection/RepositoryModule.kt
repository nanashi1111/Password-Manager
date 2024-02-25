package com.passwordmanager.data.injection

import com.passwordmanager.data.pref.DataStoreUtils
import com.passwordmanager.data.repositories.PasswordManagerRepository
import com.passwordmanager.data.repositories.PasswordManagerRepositoryImpl
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
  fun providePasswordManagerRepository(dataStoreUtils: DataStoreUtils): PasswordManagerRepository = PasswordManagerRepositoryImpl(dataStoreUtils)
}