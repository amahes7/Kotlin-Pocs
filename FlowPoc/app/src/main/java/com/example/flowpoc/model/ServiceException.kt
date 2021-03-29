package com.example.flowpoc.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ServiceException(val statusCode: Int, val errorCode: String, val errorMessage: String): Parcelable