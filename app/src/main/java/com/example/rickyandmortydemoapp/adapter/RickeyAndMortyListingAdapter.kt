package com.example.rickyandmortydemoapp.adapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.activity.RickyAndMortyDetailsActivity
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.utils.ApplicationConstant
import kotlinx.android.synthetic.main.item_rickey_and_morty.view.*

class RickeyAndMortyListingAdapter(
    private val mContext: Context,
    private val rickeyAndMortyCharacterList: ArrayList<CharacterItem>
) :
    RecyclerView.Adapter<RickeyAndMortyListingAdapter.RickeyAndMortyListingViewHolder>() {

    inner class RickeyAndMortyListingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RickeyAndMortyListingViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_rickey_and_morty, parent, false)
        return RickeyAndMortyListingViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: RickeyAndMortyListingViewHolder, position: Int) {

        val data = rickeyAndMortyCharacterList[holder.bindingAdapterPosition]

        holder.itemView.textViewName.text = data.name

        Glide.with(mContext)
            .load(data.image)
            .placeholder(R.drawable.ic_photo)
            .error(R.drawable.ic_photo)
            .into(  holder.itemView.image_view_thumbnail)

        holder.itemView.setOnClickListener {
            val intent = Intent(mContext, RickyAndMortyDetailsActivity::class.java)
            intent.putExtra(ApplicationConstant.CHARACTER_DATA, data)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(mContext as Activity, (holder.itemView.image_view_thumbnail)!!, "character")
            mContext.startActivity(intent, options.toBundle())
        }
    }

    override fun getItemCount(): Int {
        return rickeyAndMortyCharacterList.size
    }
}