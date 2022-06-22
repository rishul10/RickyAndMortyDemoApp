package com.example.rickyandmortydemoapp.utils

import com.example.rickyandmortydemoapp.model.APIError
import retrofit2.Response

object ErrorUtils {
    fun parseError(response: Response<*>?): APIError {
        val error = APIError()
        error.message = "Error"
        return error
    }
}