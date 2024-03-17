package com.passwordmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.passwordmanager.data.entities.AccountDTO

@Database(entities = [AccountDTO::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
  abstract fun accountDao(): AccountDao
}