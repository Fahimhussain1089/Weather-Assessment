package com.hussain.weatherassessmenttest.repository

import androidx.lifecycle.LiveData
import com.hussain.weatherassessmenttest.data.User
import com.hussain.weatherassessmenttest.data.UserDao

//import com.hussain.weatherapp.data.User
//import com.example.weatherapp.data.UserDao

class UserRepository(private val userDao: UserDao) {
    fun getAllUsers(): LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insertUser(user: User) = userDao.insertUser(user)

    suspend fun deleteUser(user: User) = userDao.deleteUser(user)

    suspend fun deleteUserById(userId: Int) = userDao.deleteUserById(userId)
}