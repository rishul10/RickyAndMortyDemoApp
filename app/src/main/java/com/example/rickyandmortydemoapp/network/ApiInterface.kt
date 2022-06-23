package com.example.rickyandmortydemoapp.network

import com.example.rickyandmortydemoapp.model.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiInterface {

    @GET("api/character")
    fun getCharacterListAsync(): Deferred<CharacterResponse>
}
