package com.example.userregistrationapplication.repository

import androidx.lifecycle.LiveData
import com.example.userregistrationapplication.model.ResultResponse
import com.example.userregistrationapplication.model.User
import kotlinx.coroutines.flow.Flow


interface UserRepository {

    fun getTopUserWithSequentialFlows(): LiveData<User>

    fun getFilteredUsersUsingConcurrentCalls(): LiveData<List<User>>

    fun getAllUsers(): LiveData<List<User>>

    fun getAllUsersFlow(): Flow<List<User>>

    fun getTopUser(): LiveData<User>

    fun getFlowTopUser(): Flow<User>

    suspend fun addUser(user: User)

    suspend fun updateUser(user: User)

    suspend fun deleteUser(user: User)

    suspend fun deleteAllUsers()


}