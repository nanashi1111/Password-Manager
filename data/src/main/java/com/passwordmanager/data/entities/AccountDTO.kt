package com.passwordmanager.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class AccountDTO(
  @PrimaryKey
  val date: Long,
  @ColumnInfo("title")
  val title: String,
  @ColumnInfo("username")
  val username: String,
  @ColumnInfo("password")
  val password: String,
  @ColumnInfo("website")
  val website: String,
  @ColumnInfo("note")
  val note: String
)
