package com.example.booksapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksapp.data.BookDatabase
import com.example.booksapp.data.user.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userDao = BookDatabase.getDatabase(application).userDao()
    private val repository = UserRepository(userDao)

    fun registerUser(login: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                repository.registerUser(login, password)
                onResult(true)
            } catch (e: Exception) {
                onResult(false)
            }
        }
    }

    fun loginUser(login: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val result = repository.loginUser(login, password)
            onResult(result)
        }
    }

    fun checkLoggedInUser(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val loggedIn = repository.getLoggedInUser() != null
            onResult(loggedIn)
        }
    }
}