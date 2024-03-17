package com.passwordmanager.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.passwordmanager.data.entities.AccountDTO

@Dao
interface AccountDao {

  @Query("select * from AccountDTO")
  suspend fun getAllAccount(): List<AccountDTO>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun createNewAccount(accountDTO: AccountDTO)

  @Query("select * from AccountDTO where date = :createdDate")
  suspend fun getAccount(createdDate: Long): AccountDTO?
}