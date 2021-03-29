package com.example.userregistrationapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.userregistrationapplication.data.UserDao
import com.example.userregistrationapplication.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {

    lateinit var body: List<User>
    lateinit var status: String
    lateinit var message: String

    override fun getTopUserWithSequentialFlows(): LiveData<User> {
        val flow1 = getAllUsersFlow()
        val comparator: Comparator<User> = Comparator.comparing(User::id)
        return flow1.map { users -> users.stream().max(comparator).get() }.asLiveData()
    }

    override fun getFilteredUsersUsingConcurrentCalls(): LiveData<List<User>> {
        return combine(
            getAllUsersFlowlimit1(),
            getAllUsersFlowLimit3()
        ) { elements: Array<List<User>> ->
            elements.flatMap { it }
        }
            .asLiveData()
    }

    override fun getAllUsersFlow(): Flow<List<User>> = flow {
        val response = userDao.getAllUsersFlow()
        emit(response)
    }.flowOn(Dispatchers.IO)


    private fun getAllUsersFlowlimit1(): Flow<List<User>> =
        userDao.getAllUsersFlow1limit1()

    private fun getAllUsersFlowLimit3(): Flow<List<User>> =
        userDao.getAllUsersFlowLimit3()


    override fun getTopUser(): LiveData<User> {
        return userDao.getTopUser()
    }

    override fun getFlowTopUser(): Flow<User> {
        return userDao.getFlowTopUser()
    }

    override suspend fun addUser(user: User) {
        userDao.insertUser(user)
    }

    override suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    override suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    override suspend fun deleteAllUsers() {
        userDao.deleteAllUsers()
    }

    override fun getAllUsers(): LiveData<List<User>> {
        return userDao.getAllUsers()
    }

}


