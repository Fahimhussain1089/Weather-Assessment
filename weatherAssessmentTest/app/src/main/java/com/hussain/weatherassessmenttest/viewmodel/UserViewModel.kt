package com.hussain.weatherassessmenttest.viewmodel


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.hussain.weatherassessmenttest.data.AppDatabase
import com.hussain.weatherassessmenttest.data.User
import com.hussain.weatherassessmenttest.repository.UserRepository

import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository
    val allUsers: LiveData<List<User>>

    init {
        val userDao = AppDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        allUsers = repository.getAllUsers()
    }

    fun insertUser(user: User) = viewModelScope.launch {
        repository.insertUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    fun deleteUserById(userId: Int) = viewModelScope.launch {
        repository.deleteUserById(userId)
    }
}