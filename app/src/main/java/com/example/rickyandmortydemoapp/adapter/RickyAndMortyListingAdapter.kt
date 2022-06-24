package com.example.rickyandmortydemoapp.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.viewholder.RickeyAndMortyListingViewHolder

class RickyAndMortyListingAdapter :
    PagingDataAdapter<CharacterItem, RickeyAndMortyListingViewHolder>(REPO_COMPARATOR) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickeyAndMortyListingViewHolder {
        return RickeyAndMortyListingViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RickeyAndMortyListingViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<CharacterItem>() {
            override fun areItemsTheSame(oldItem: CharacterItem, newItem: CharacterItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: CharacterItem,
                newItem: CharacterItem
            ): Boolean =
                oldItem == newItem
        }
    }
}