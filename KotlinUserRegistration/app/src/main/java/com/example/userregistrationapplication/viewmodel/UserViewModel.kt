package com.example.userregistrationapplication.viewmodel

import android.app.Application
import android.provider.SyncStateContract
import androidx.lifecycle.*
import com.example.userregistrationapplication.data.UserDatabase
import com.example.userregistrationapplication.model.ResultResponse
import com.example.userregistrationapplication.model.User
import com.example.userregistrationapplication.repository.UserRepositoryImpl
import com.example.userregistrationapplication.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class UserViewModel(application: Application) : AndroidViewModel(application) {


    lateinit var getAllUsersByFlow: MutableLiveData<List<User>>

    //    lateinit var getAllUsersByFlowNew: MutableLiveData<List<User>>
    val concurrentResponse: LiveData<List<User>>
    val getTopUserWithSequentialFlows: LiveData<User>
//    lateinit var resultResponse: ResultResponse
//    lateinit var status: String
//    lateinit var message: String

    private val repository: UserRepositoryImpl

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepositoryImpl(userDao)

//Concurrent calls using combine for List fragment
        concurrentResponse = repository.getFilteredUsersUsingConcurrentCalls()


// sequential flows Implementation for top user Fragment
        getTopUserWithSequentialFlows = repository.getTopUserWithSequentialFlows()
        getAllUsersByFlow()

// Single API Call to get All Users
//        viewModelScope.launch(Dispatchers.IO) {
//            getAllUsersByFlow = repository.getAllUsersByFlow().body
//        }
//    }


//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getAllUsersByFlow()
//                .catch {
//                    status = Constants.FAILURE_TEXT
//                    message = Constants.FAILURE_TEXT
//                }.collect { resp ->
//                    status = Constants.SUCCESS_TEXT
//                    message = Constants.SUCCESS_MESSAGE
//                    getAllUsersByFlowNew.value = resp
//                }
//        }
//        resultResponse.status = status
//        resultResponse.message = message
//        resultResponse.body = getAllUsersByFlowNew
//    }
    }

    fun getAllUsersByFlow() {
        viewModelScope.launch(Dispatchers.IO) {
            getAllUsersByFlow = repository.getAllUsersByFlow().body
        }
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }

    fun deleteUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

    fun deleteAllUsers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllUsers()
        }
    }

}


// Try Code for reference

//    val getAllUsersFromLiveDataDao: LiveData<List<User>>
//    val getTopUserFromQuery: LiveData<User>
//    val getFlowTopUser: LiveData<User>
// LiveData from Dao
//        getAllUsersFromLiveDataDao = repository.getAllUsers()
// get Top User by liveData
//        getTopUserFromQuery = repository.getTopUser()
//get Top suer by Flow
//        getFlowTopUser = repository.getFlowTopUser()
//            .catch {
//error log
//            }
//            .asLiveData()