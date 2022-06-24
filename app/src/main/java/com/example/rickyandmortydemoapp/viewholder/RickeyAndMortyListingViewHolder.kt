package com.example.rickyandmortydemoapp.viewholder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.model.CharacterItem
import kotlinx.android.synthetic.main.item_rickey_and_morty.view.*

class RickeyAndMortyListingViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bind(characterItem: CharacterItem?) {

        itemView.textViewName.text = characterItem?.name

        Glide.with(itemView.image_view_thumbnail)
            .load(characterItem?.image)
            .placeholder(R.drawable.ic_photo)
            .error(R.drawable.ic_photo)
            .into(itemView.image_view_thumbnail)
    }

    companion object {
        fun create(parent: ViewGroup): RickeyAndMortyListingViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_rickey_and_morty, parent, false)
            return RickeyAndMortyListingViewHolder(view)
        }
    }
}