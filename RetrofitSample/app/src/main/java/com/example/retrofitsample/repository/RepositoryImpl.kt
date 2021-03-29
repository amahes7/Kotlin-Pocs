package com.example.retrofitsample.repository

import com.example.myfirstapp.api.RetrofitInstance
import com.example.myfirstapp.model.ResponseObject
import com.example.myfirstapp.repository.Repository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.withContext
import retrofit2.Response

class RepositoryImpl : Repository {


    override suspend fun getData(): Response<ResponseObject.DataModel> {
        val result = withContext(coroutinesDispatcherProvider.io) {
            flowOf(processCall { apiClient.getData1() }).map { result ->
                {
                    println("Testing")
                }
            }.collect()
        }
    }
}
