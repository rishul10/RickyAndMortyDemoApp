package com.example.rickyandmortydemoapp.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickyandmortydemoapp.repository.UserRepository

class CharactersPagingSource(
    private var userRepository: UserRepository
) : PagingSource<Int, CharacterItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterItem> {
        return try {
            val nextPageNumber = params.key ?: 0
            val response: CharacterResponse =
                userRepository.getCharacterListAsync(pageNo = nextPageNumber).await()

            LoadResult.Page(
                data = response.results,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.info?.pages!!) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CharacterItem>): Int? {
        return null
    }
}