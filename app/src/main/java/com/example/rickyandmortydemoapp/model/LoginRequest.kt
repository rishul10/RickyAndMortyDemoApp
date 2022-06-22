package com.example.rickyandmortydemoapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LoginRequest(
    var username: String? = "",
    var password: String? = ""
) : Parcelable