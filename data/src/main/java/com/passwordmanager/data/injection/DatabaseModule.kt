package com.passwordmanager.data.injection

import android.content.Context
import androidx.room.Room
import com.passwordmanager.data.database.AccountDao
import com.passwordmanager.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
  @Provides
  @Singleton
  fun provideAccountDao(@ApplicationContext context: Context): AccountDao {
    return Room.databaseBuilder(context, AppDatabase::class.java, "MaxPassword")
      .fallbackToDestructiveMigration()
      .build()
      .accountDao()
  }
}