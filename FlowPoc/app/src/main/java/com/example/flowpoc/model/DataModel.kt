package com.example.flowpoc.model

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.flowpoc.activity.main.ResponseState
import kotlinx.android.parcel.Parcelize

sealed class ResponseObject {
    @Parcelize
    data class DataModel(
            val page: Int,
            val per_page: Int,
            val total: Int,
            val total_pages: Int,
            val data: List<Users>
    ) : BaseResponse(), Parcelable


    @Parcelize
    data class DataModel2(
            val page: Int,
            val per_page: Int,
            val total: Int,
            val total_pages: Int,
            val data: List<Users2>
    ) : BaseResponse(), Parcelable


    @Parcelize
    data class Users(
            @PrimaryKey val id: Int,
            val first_name: String,
            val last_name: String,
            val avatar: String
    ) : BaseResponse(), Parcelable

    @Parcelize
    data class Users2(
            @PrimaryKey val id: Int,
            val first_name: String,
            val last_name: String,
            val email: String,
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

    @SuppressLint("ParcelCreator")
    data class ResultResponseWrapper<T : Parcelable>(
            val statusCode: String,
            val message: String,
            val data: List<T>
    ) : BaseResponse(), Parcelable
}
