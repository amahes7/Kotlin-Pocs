package com.example.myfirstapp.repository

import com.example.myfirstapp.api.RetrofitInstance
import com.example.myfirstapp.model.ResponseObject
import retrofit2.Response

interface Repository {

//    suspend fun getPost(): Response<ResponseObject.Post> {
//        return RetrofitInstance.api.getPost()
//    }
//
//    suspend fun getPostByNumber(number: Int): Response<ResponseObject.Post> {
//        return RetrofitInstance.api.getPostByNumber(number)
//    }
//
//    suspend fun getAllPosts(): Response<List<ResponseObject.Post>> {
//        return RetrofitInstance.api.getAllPosts()
//    }

    suspend fun getData(): Response<ResponseObject.DataModel>
}