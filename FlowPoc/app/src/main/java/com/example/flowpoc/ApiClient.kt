package com.example.flowpoc

import com.example.flowpoc.model.ResponseObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {

//    @GET("/api/users?page=2")
//    suspend fun getData(): DataModel
//
//    @GET("/error")
//    suspend fun getError(): DataModel

    @GET("/api/users?page=2")
    suspend fun getData1(): Response<ResponseObject.DataModel2>

    @GET("/error")
    suspend fun getError(): Response<ResponseObject.DataModel>


    @GET("/api/users?page=1")
    suspend fun getData2(): Response<ResponseObject.DataModel2>

}