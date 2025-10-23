package com.example.booksapp.data.user

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey val login: String,
    val password: String,
    val isLoggedIn: Boolean = false
)
