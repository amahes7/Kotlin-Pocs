package com.example.userregistrationapplication.model

import androidx.lifecycle.MutableLiveData

class ResultResponse(
    var status: String,
    var message: String,
    var body: MutableLiveData<List<User>>
)