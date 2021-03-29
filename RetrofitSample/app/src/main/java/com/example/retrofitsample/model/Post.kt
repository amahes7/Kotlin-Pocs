package com.example.myfirstapp.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

sealed class ResponseObject {
//    data class Post(
//        @SerializedName("userId")
//        val myUser: Int,
//        val id: Int,
//        val title: String,
//        val body: String
//    )

    @Parcelize
    data class DataModel(
        val page: Int,
        val per_page: Int,
        val total: Int,
        val total_pages: Int,
        val data: List<Users>
    ) : BaseResponse(), Parcelable


    @Parcelize
    data class Users(
        val id: Int,
        val first_name: String,
        val last_name: String,
        val avatar: String
    ) : BaseResponse(), Parcelable

    @Parcelize
    open class BaseResponse(
        val code: Int = 0,
        val result: Int = 0,
        val errorCodes: ArrayList<Int> = ArrayList(),
        val errorMessages: ArrayList<String?> = ArrayList(),
        val appVersion: String = "1.0",
        val appRelease: String = "1"
    ) : ResponseObject(), Parcelable
}