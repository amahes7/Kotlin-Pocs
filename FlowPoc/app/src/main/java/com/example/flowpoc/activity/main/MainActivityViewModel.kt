package com.example.flowpoc.activity.main

//import com.example.flowpoc.db.UsersDao
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flowpoc.ApiClient
import com.example.flowpoc.CoroutinesDispatcherProvider
import com.example.flowpoc.model.ResponseObject
import com.example.flowpoc.model.ServiceResult
import com.example.flowpoc.repository.UserRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
class MainActivityViewModel
@Inject constructor(
        private val apiClient: ApiClient,
        private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
        private val repository: UserRepositoryImpl

) : ViewModel() {
    private val _state: MutableLiveData<ResponseState> = MutableLiveData()
    val state: LiveData<ResponseState> = _state
    private val _users: MutableLiveData<List<ResponseObject.Users>> = MutableLiveData()
    val users: LiveData<List<ResponseObject.Users>> = _users

    private val _wrapper: MutableLiveData<ResponseObject.ResultResponseWrapper<ResponseObject.Users>> = MutableLiveData()
    val wrapper: LiveData<ResponseObject.ResultResponseWrapper<ResponseObject.Users>> = _wrapper


    private val _wrapper2: MutableLiveData<ResponseObject.ResultResponseWrapper<ResponseObject.Users2>> = MutableLiveData()
    val wrapper2: LiveData<ResponseObject.ResultResponseWrapper<ResponseObject.Users2>> = _wrapper2
    init {
//        _state.postValue(ResponseState.ShowLoading)
//        getDataFromRoomDBWithCombine()
//        getDataFromAPI()
//        getDataWithRoomDb()
//        getDataFromAPIWithError()
//        getDataFromRoomDBAndAPIWithCombine()
//        seqFlowCalls()
//        repoCall()
//        repoCallNew()
//        repoCallWithZip()
//        repoCallWithZipForUser2()
        repoCallWithZipForUser2SingleApi()
    }

//    fun repoCall() {
//        println(" ======> repoCall start===============")
//        viewModelScope.launch {
//            val resp = repository.getDataFromAPI()
//            resp.map {
//                if (it is ServiceResult.Success) {
//                    if (!it.data.data.isNullOrEmpty()) {
//                        println("Size ======> ${it.data.data.size}")
//                        _users.postValue(it.data.data)
//                    }
//                } else if (it is ServiceResult.Error) {
//                    _state.postValue(
//                            ResponseState.ErrorState(it.error.errorMessage))
//                }
//            }.collect()
//        }
//    }


//    private fun repoCallNew() {
//        println(" ======> repoCall start===============")
//        val call = ""
//        viewModelScope.launch {
//            repository.getDataFromAPINew()?.map {
//                _wrapper.postValue(it)
//            }?.collect()
//        }
//    }
//
//
//    private fun repoCallWithZip() {
//        println(" ======> repoCall start===============")
//        val call = ""
//        viewModelScope.launch {
//            repository.getDataFromAPINewWithZip()?.map {
//                _wrapper.postValue(it)
//            }?.collect()
//        }
//    }


    private fun repoCallWithZipForUser2() {
        println(" ======> repoCall start===============")
        val call = ""
        viewModelScope.launch {
            repository.getDataFromAPINewWithZipData2()?.map {
                _wrapper2.postValue(it)
            }?.collect()
        }
    }

    private fun repoCallWithZipForUser2SingleApi() {
        println(" ======> repoCall start===============")
        val call = ""
        viewModelScope.launch {
            repository.getDataFromAPINewWithZipData2SingleAPI()?.map {
                _wrapper2.postValue(it)
            }?.collect()
        }
    }

//    private fun getDataWithRoomDb() {
//        viewModelScope.launch {
//            withContext(coroutinesDispatcherProvider.io) {
//                flowOf(apiClient.getData())
//                    .catch { ex ->
//                        println(ex.stackTrace)
//                        _state.postValue(
//                            ServiceResponse.ShowError(
//                                ex
//                            )
//                        )
//                    }
//                    .map { result ->
//                        if (!result.data.isNullOrEmpty()) {
//                            usersDao.deleteAllUsers()
//                            usersDao.insertUsers(result.data)
//                        }
//                    }.collect()
//            }
//        }
//    }
//
//    private fun getDataFromRoomDBAndAPIWithZip() {
//        viewModelScope.launch {
//            withContext(coroutinesDispatcherProvider.io) {
//                try {
//                    usersDao.getOneUser()
//                        .zip(flowOf(apiClient.getData())) { databaseUsers, apiUsers ->
//                            val allUsers = mutableListOf<Users>()
//                            allUsers.addAll(databaseUsers)
//                            allUsers.addAll(apiUsers.data)
//                            return@zip allUsers
//                        }
//                        .map {
//                            if (it.isNotEmpty()) {
//                                println("Size ======> ${it.size}")
//                                _users.postValue(it)
//                            } else {
//                                _state.postValue(ServiceResponse.ErrorState("No Data Found in the DB"))
//                            }
//                        }
//                        .collect()
//                } catch (ex: Exception) {
//                    println("=========================API EXCEPTION============================================")
//                    println(ex.stackTrace)
//                    _state.postValue(
//                        ServiceResponse.ShowError(ex)
//                    )
//                }
//            }
//
//        }
//    }
//
//
//    private fun getDataFromAPI() {
//        viewModelScope.launch {
//            try {
//                flowOf(apiClient.getData())
//                    .map { result ->
//                        if (!result.data.isNullOrEmpty()) {
//                            _users.postValue(result.data)
//                        }
//                    }.collect { }
//            } catch (ex: Exception) {
//                println("=====================================================================")
//                println(ex.stackTrace)
//                _state.postValue(
//                    ServiceResponse.ShowError(ex)
//                )
//            }
//        }
//    }
//
//
//    private fun getDataFromAPIWithError() {
//        viewModelScope.launch {
//            try {
//                flowOf(apiClient.getError())
//                    .map { result ->
//                        if (!result.data.isNullOrEmpty()) {
//                            _users.postValue(result.data)
//                        }
//
//                    }.collect { }
//            } catch (ex: Exception) {
//                println("=====================================================================")
//                println(ex.stackTrace)
//                _state.postValue(
//                    ServiceResponse.ShowError(ex)
//                )
//            }
//        }
//    }
//
//    private fun getDataFromRoomDBWithCombine() {
//        viewModelScope.launch {
//            withContext(coroutinesDispatcherProvider.io) {
//                try {
//                    combine(
//                        usersDao.getOneUser(),
//                        usersDao.getTwoUsers()
//                    ) { elements: Array<List<Users>> ->
//                        elements.flatMap { it }
//                    }.map {
//                        if (it.isNotEmpty()) {
//                            _users.postValue(it)
//                        } else {
//                            _state.postValue(ServiceResponse.ErrorState("No Data Found in the DB"))
//                        }
//                    }.collect()
//                } catch (ex: Exception) {
//                    println("=========================API EXCEPTION============================================")
//                    println(ex.stackTrace)
//                    _state.postValue(
//                        ServiceResponse.ShowError(ex)
//                    )
//                }
//            }
//
//        }
//    }
//
//    private fun seqFlowCalls() {
//        viewModelScope.launch {
//            withContext(coroutinesDispatcherProvider.io) {
//                try {
//                    val oneUserFlow = usersDao.getOneUser()
//                    val TwoUserFlow = usersDao.getTwoUsers()
//                    val flow1 = oneUserFlow
//                        .zip(TwoUserFlow) { oneUserFlow, TwoUserFlow ->
//                            val allUsers = mutableListOf<Users>()
//                            allUsers.addAll(oneUserFlow)
//                            allUsers.addAll(TwoUserFlow)
//                            return@zip allUsers
//                        }
//                    println("====> here1")
//                    val flow2 = flow1
//                        .zip(flowOf(apiClient.getData())) { databaseUsers, apiUsers ->
//                            val allUsers = mutableListOf<Users>()
//                            allUsers.addAll(databaseUsers)
//                            allUsers.addAll(apiUsers.data)
//                            return@zip allUsers
//                        }
//                        .map {
//                            if (it.isNotEmpty()) {
//                                println("Size ======> ${it.size}")
//                                _users.postValue(it)
//                            } else {
//                                _state.postValue(ServiceResponse.ErrorState("No Data Found in the DB"))
//                            }
//                        }.delayFlow(10000)
//                        .collect()
//                } catch (ex: Exception) {
//                    println("=========================API EXCEPTION============================================")
//                    println(ex.stackTrace)
//                    _state.postValue(
//                        ServiceResponse.ShowError(ex)
//                    )
//                }
//            }
//        }
//    }

}

