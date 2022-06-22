package com.example.rickyandmortydemoapp.repository

import com.example.rickyandmortydemoapp.base.BaseRepository
import com.example.rickyandmortydemoapp.model.LoginRequest
import com.example.rickyandmortydemoapp.model.LoginResponse
import com.example.rickyandmortydemoapp.network.ApiInterface
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepository @Inject constructor(var apiService: ApiInterface) :
    BaseRepository() {

    fun callLoginRepoAsync(user: LoginRequest): Deferred<LoginResponse> {
        return apiService.callLoginAsync(user)
    }
}