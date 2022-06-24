package com.example.rickyandmortydemoapp.network

import com.example.rickyandmortydemoapp.model.CharacterResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {

    @GET("api/character")
     fun getCharacterListAsync(@Query("page") pageNo: Int): Deferred<CharacterResponse>

    @GET("api/character")
    fun getSearchCharacterListAsync(@Query("name") CharacterName: String): Deferred<CharacterResponse>
}
