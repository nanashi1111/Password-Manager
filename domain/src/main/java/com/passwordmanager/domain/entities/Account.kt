package com.passwordmanager.domain.entities

import android.graphics.Color
import com.passwordmanager.data.entities.AccountDTO

data class Account(
    val date: Long,
    val title: String,
    val username: String,
    val password: String,
    val website: String,
    val note: String,
    val color: String,
) {

    companion object {
        fun fromDto(accountDTO: AccountDTO): Account {
            return Account(
                date = accountDTO.date,
                title = accountDTO.title,
                username = accountDTO.username,
                password = accountDTO.password,
                website = accountDTO.website,
                note = accountDTO.note,
                color = accountDTO.color
            )
        }
    }

}

enum class AccountField {
    TITLE, USERNAME, PASSWORD, WEBSITE, NOTE, COLOR
}