package com.example.retrofitsample

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myfirstapp.model.Post
import com.example.myfirstapp.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: Repository) : ViewModel() {

    val myResponse: MutableLiveData<Response<Post>> = MutableLiveData();
    val myResponse1: MutableLiveData<Response<Post>> = MutableLiveData();
    val myResponse2: MutableLiveData<Response<List<Post>>> = MutableLiveData();

    fun getPost() {
        viewModelScope.launch {
            val response = repository.getPost()
            myResponse.value = response;
        }
    }


    fun getPostByNumber(number: Int) {
        viewModelScope.launch {
            val response = repository.getPostByNumber(number)
            myResponse1.value = response;
        }
    }

    fun getAllPosts() {
        viewModelScope.launch {
            val response = repository.getAllPosts()
            myResponse2.value = response;
        }
    }
}

