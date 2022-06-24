package com.example.rickyandmortydemoapp.repository

import com.example.rickyandmortydemoapp.base.BaseRepository
import com.example.rickyandmortydemoapp.model.CharacterResponse
import com.example.rickyandmortydemoapp.network.ApiInterface
import kotlinx.coroutines.Deferred
import javax.inject.Inject

class UserRepository @Inject constructor(var apiService: ApiInterface) :
    BaseRepository() {

    fun getCharacterListAsync(pageNo: Int): Deferred<CharacterResponse> {
        return apiService.getCharacterListAsync(pageNo)
    }

    fun getSearchCharacterListAsync(searchedData: String): Deferred<CharacterResponse> {
        return apiService.getSearchCharacterListAsync(searchedData)
    }

}