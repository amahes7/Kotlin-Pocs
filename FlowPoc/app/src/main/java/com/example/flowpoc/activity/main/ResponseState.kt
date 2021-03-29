package com.example.flowpoc.activity.main

import com.example.flowpoc.model.ResponseObject

sealed class ResponseState {
    object ShowLoading : ResponseState()
    class ShowError(val error: Throwable) : ResponseState()
    class ErrorState(val message: String) : ResponseState()
    class ShowData(val data: List<ResponseObject.Users>) : ResponseState()
}