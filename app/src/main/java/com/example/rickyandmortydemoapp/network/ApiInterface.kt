package com.example.rickyandmortydemoapp.network

import com.example.rickyandmortydemoapp.model.LoginRequest
import com.example.rickyandmortydemoapp.model.LoginResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {

    @POST("users/sign_in")
    fun callLoginAsync(@Body request: LoginRequest): Deferred<LoginResponse>
}
