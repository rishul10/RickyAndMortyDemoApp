package com.example.rickyandmortydemoapp.model

class APIError {
    //private val statusCode = 0
    var message: String? = null
    var error: String? = null
        get() = if (field == null) {
            message
        } else field
}