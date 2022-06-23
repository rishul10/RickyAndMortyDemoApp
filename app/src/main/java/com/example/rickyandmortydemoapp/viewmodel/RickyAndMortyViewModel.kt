package com.example.rickyandmortydemoapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.rickyandmortydemoapp.model.CharacterResponse
import com.example.rickyandmortydemoapp.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RickyAndMortyViewModel @Inject constructor(private var userRepository: UserRepository) :
    BaseViewModel() {

    private var _characterListResponse = MutableLiveData<CharacterResponse>()
    val characterListResponse: LiveData<CharacterResponse> get() = _characterListResponse // backing field

    fun getCharacterList() {
        executeApiCallByCoroutine {
            val response = userRepository.getCharacterListAsync().await()
            withContext(Dispatchers.Main) {
                _characterListResponse.value = (response)
            }
        }
    }
}