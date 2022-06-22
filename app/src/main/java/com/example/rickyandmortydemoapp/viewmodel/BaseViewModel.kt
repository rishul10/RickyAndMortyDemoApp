package com.example.rickyandmortydemoapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmortydemoapp.utils.ErrorUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException

open class BaseViewModel : ViewModel() {
    val progressBar = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<Pair<Int, String>>()

    fun executeApiCallByCoroutine(execute: suspend () -> Unit) {
        progressBar.postValue(true) // show loader
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    execute()
                }
            } catch (e: HttpException) {
                val errorResponse = ErrorUtils.parseError(e.response())
                errorLiveData.value = Pair(e.code(), errorResponse.error ?: "")
            } finally {
                progressBar.value = false// dismiss loader
            }
        }
    }

    //This function used for APis like pagination where loader is being handled differently
    fun executeApiCallByCoroutineWithoutLoader(execute: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    execute()
                }
            } catch (e: HttpException) {
                val errorResponse = ErrorUtils.parseError(e.response())
                errorLiveData.value = Pair(e.code(), errorResponse.error ?: "")
            } finally {
                progressBar.value = false// dismiss loader
            }
        }
    }
}