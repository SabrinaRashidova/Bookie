package com.example.booksapp.data.user

class UserRepository(private val userDao: UserDao) {

    suspend fun registerUser(login: String, password: String) {
        val user = User(login, password, isLoggedIn = true)
        userDao.logoutAllUsers()
        userDao.insertUser(user)
    }

    suspend fun loginUser(login: String, password: String): Boolean {
        val user = userDao.getUserByLogin(login)
        return if (user != null && user.password == password) {
            userDao.logoutAllUsers()
            userDao.insertUser(user.copy(isLoggedIn = true))
            true
        } else false
    }

    suspend fun getLoggedInUser(): User? = userDao.getLoggedInUser()

    suspend fun logout() = userDao.logoutAllUsers()
}