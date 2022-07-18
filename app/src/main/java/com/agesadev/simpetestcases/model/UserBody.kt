package com.agesadev.simpetestcases.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserBody(
    val name: String,
    @PrimaryKey
    val email: String
) {
}