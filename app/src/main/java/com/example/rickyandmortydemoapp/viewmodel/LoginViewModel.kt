package com.example.rickyandmortydemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickyandmortydemoapp.model.LoginRequest
import com.example.rickyandmortydemoapp.model.LoginResponse
import com.example.rickyandmortydemoapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor(private var userRepository: UserRepository) :
    BaseViewModel() {

    private var _loginResponse = MutableLiveData<LoginResponse>()
    val loginResponse: LiveData<LoginResponse> get() = _loginResponse // backing field

    fun callLogin(loginRequest: LoginRequest) {
        executeApiCallByCoroutine {
            val response = userRepository.callLoginRepoAsync(loginRequest).await()
            withContext(Dispatchers.Main) {
                _loginResponse.value = (response)
            }
        }
    }
}