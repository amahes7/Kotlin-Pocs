package com.example.flowpoc.repository

//import com.example.flowpoc.db.UsersDao
import com.example.flowpoc.ApiClient
import com.example.flowpoc.CoroutinesDispatcherProvider
import com.example.flowpoc.activity.main.ResponseState
import com.example.flowpoc.model.ExceptionType
import com.example.flowpoc.model.ResponseObject
import com.example.flowpoc.model.ResponseObject.ResultResponseWrapper
import com.example.flowpoc.model.ServiceException
import com.example.flowpoc.model.ServiceResult
import com.example.flowpoc.route.Constants
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl
@Inject constructor(
        private val apiClient: ApiClient,
        private val coroutinesDispatcherProvider: CoroutinesDispatcherProvider,
//    private val usersDao: UsersDao

) {
//    suspend fun getDataFromAPI(): Flow<ServiceResult<ResponseObject.DataModel>> {
//        println(" ======> getDataFromAPI start")
//        return withContext(coroutinesDispatcherProvider.io) {
//            flowOf(processCall { apiClient.getData1() })
////            flowOf(processCall { apiClient.getError() })
//        }
//    }

//    suspend fun getDataFromAPINew(): Flow<ResultResponseWrapper<ResponseObject.Users>>? {
//        println(" ======> getDataFromAPI start")
//        var responseWrapper: Flow<ResultResponseWrapper<ResponseObject.Users>>? = null
//        withContext(coroutinesDispatcherProvider.io) {
//            flowOf(processCall { apiClient.getData1() })
//                    .map {
//                        val allUsers = mutableListOf<ResponseObject.Users>()
//                        println("Inside Map ======> ")
//                        if (it is ServiceResult.Success) {
//                            if (!it.data.data.isNullOrEmpty()) {
//                                allUsers.addAll(it.data.data)
//                                println("Size ======> ${it.data.data.size}")
//                                responseWrapper = flowOf(ResultResponseWrapper("200", "", allUsers))
//                            }
//                        } else if (it is ServiceResult.Error) {
//                            responseWrapper = flowOf(ResultResponseWrapper(it.error.errorCode, it.error.errorMessage, emptyList()))
//                        }
//                    }.collect()
////            flowOf(processCall { apiClient.getError() })
//        }
//        return responseWrapper
//    }

//    suspend fun getDataFromAPINewWithZip(): Flow<ResultResponseWrapper<ResponseObject.Users>>? {
//        println(" ======> getDataFromAPI start")
//        var responseWrapper: Flow<ResultResponseWrapper<ResponseObject.Users>>? = null
//        withContext(coroutinesDispatcherProvider.io) {
//            flowOf(processCall { apiClient.getData1() })
//                    .zip(flowOf(processCall { apiClient.getData1() })) { flow1, flow2 ->
//                        if (flow1 is ServiceResult.Success && flow2 is ServiceResult.Success) {
//                            val allUsers = mutableListOf<ResponseObject.Users>()
//                            allUsers.addAll(flow1.data.data)
//                            allUsers.addAll(flow2.data.data)
//                            responseWrapper = flowOf(ResultResponseWrapper("200", "", allUsers))
//                        } else if (flow1 is ServiceResult.Error) {
//                            responseWrapper = flowOf(ResultResponseWrapper(flow1.error.errorCode, flow1.error.errorMessage, emptyList()))
//                        } else if (flow2 is ServiceResult.Error) {
//                            responseWrapper = flowOf(ResultResponseWrapper(flow2.error.errorCode, flow2.error.errorMessage, emptyList()))
//                        }
//                    }.collect()
//        }
//        return responseWrapper
//    }


    suspend fun getDataFromAPINewWithZipData2(): Flow<ResultResponseWrapper<ResponseObject.Users2>>? {
        println(" ======> getDataFromAPI start")
        var responseWrapper: Flow<ResultResponseWrapper<ResponseObject.Users2>>? = null
        withContext(coroutinesDispatcherProvider.io) {
            flowOf(processCall { apiClient.getData2() })
                    .zip(flowOf(processCall { apiClient.getData1() })) { flow1, flow2 ->
                        if (flow1 is ServiceResult.Success && flow2 is ServiceResult.Success) {
                            println("=============INSIDE===================")
                            val allUsers = mutableListOf<ResponseObject.Users2>()
                            allUsers.addAll(flow1.data.data)
                            allUsers.addAll(flow2.data.data)
                            responseWrapper = flowOf(ResultResponseWrapper("200", "", allUsers))
                        } else if (flow1 is ServiceResult.Error) {
                            println("=============INSIDE1===================")
                            responseWrapper = flowOf(ResultResponseWrapper(flow1.error.errorCode, flow1.error.errorMessage, emptyList()))
                        } else if (flow2 is ServiceResult.Error) {
                            println("=============INSIDE2===================")
                            responseWrapper = flowOf(ResultResponseWrapper(flow2.error.errorCode, flow2.error.errorMessage, emptyList()))
                        }
                    }.collect()
        }
        return responseWrapper
    }


    suspend fun getDataFromAPINewWithZipData2SingleAPI(): Flow<ResultResponseWrapper<ResponseObject.Users2>>? {
        println(" ======> getDataFromAPI start")
        var responseWrapper: Flow<ResultResponseWrapper<ResponseObject.Users2>>? = null
        withContext(coroutinesDispatcherProvider.io) {
            flowOf(processCall { apiClient.getData2() })
                    .map {
                        val allUsers = mutableListOf<ResponseObject.Users2>()
                        println("Inside Map ======> ")
                        if (it is ServiceResult.Success) {
                            if (!it.data.data.isNullOrEmpty()) {
                                allUsers.addAll(it.data.data)
                                println("Size ======> ${it.data.data.size}")
                                responseWrapper = flowOf(ResultResponseWrapper("200", "", allUsers))
                            }
                        } else if (it is ServiceResult.Error) {
                            responseWrapper = flowOf(ResultResponseWrapper(it.error.errorCode, it.error.errorMessage, emptyList()))
                        }
                    }.collect()
        }
        return responseWrapper
    }


    //    suspend fun getDataFromAPINew(): Flow<ResultResponseWrapper<ResponseObject.Users>>? {
//        println(" ======> getDataFromAPI start")
//        var responseWrapper: Flow<ResultResponseWrapper<ResponseObject.Users>>? = null
//        withContext(coroutinesDispatcherProvider.io) {
//            flowOf(processCall { apiClient.getData1() })
//                    .map {
//                        println("Inside Map ======> ")
//                        if (it is ServiceResult.Success) {
//                            if (!it.data.data.isNullOrEmpty()) {
//                                println("Size ======> ${it.data.data.size}")
//                                responseWrapper = flowOf(ResultResponseWrapper("200", "", it.data.data))
//                            }
//                        } else if (it is ServiceResult.Error) {
//                            responseWrapper = flowOf(ResultResponseWrapper(it.error.errorCode, it.error.errorMessage, null))
//                        }
//                    }.collect()
////            flowOf(processCall { apiClient.getError() })
//        }
//        return responseWrapper
//    }
//
    private suspend fun <T> processCall(
            call: suspend () -> Response<T>
    ): ServiceResult<T> {
        println(" ======> processCall start===============")
        return try {
            val serviceCallback = call()
            val body = serviceCallback.body()
            if (serviceCallback.isSuccessful && body != null) {
                println(" ======> processCall Success===============" + body)
                ServiceResult.Success(body)
            } else if (!serviceCallback.isSuccessful && serviceCallback.errorBody() != null) {
                println(" ======> processCall Fail===============")
                ServiceResult.Error(processErrorBody(serviceCallback))
            } else {
                ServiceResult.Error(
                        ServiceException(
                                serviceCallback.code(),
                                serviceCallback.code().toString(),
                                serviceCallback.message().orEmpty()
                        )
                )
            }
        } catch (exception: Exception) {
            when (exception) {
                is IOException -> {
                    ServiceResult.Error(ExceptionType.NETWORK_ERROR.serviceException)
                }
                else -> {
                    ServiceResult.Error(ExceptionType.GENERIC_ERROR.serviceException)
                }
            }

        }
    }

    private fun <T> processErrorBody(response: Response<T>): ServiceException {
        val type = object : TypeToken<ResponseObject.BaseResponse>() {}.type
        val errorResponse: ResponseObject.BaseResponse? =
                Gson().fromJson(response.errorBody()?.charStream(), type)
        return ServiceException(
                response.code(),
                errorResponse?.errorCodes?.first().toString(),
                errorResponse?.errorMessages?.first().toString()
        )
    }

}