package com.example.myfirstapp.api

import com.example.myfirstapp.model.ResponseObject
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

//    @GET("/posts/1")
//    suspend fun getPost(): Response<ResponseObject.Post>
//
//    @GET("/posts/{postNumber}")
//    suspend fun getPostByNumber(
//        @Path("postNumber") num: Int
//    ): Response<ResponseObject.Post>
//
//    @GET(value = "/posts")
//    suspend fun getAllPosts(
//    ): Response<List<ResponseObject.Post>>


    @GET("/api/users?page=2")
    suspend fun getData(): Response<ResponseObject.DataModel>

}