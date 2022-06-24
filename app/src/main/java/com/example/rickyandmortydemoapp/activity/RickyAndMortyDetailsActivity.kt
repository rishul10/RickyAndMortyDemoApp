package com.example.rickyandmortydemoapp.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.base.BaseActivity
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.utils.ApplicationConstant
import kotlinx.android.synthetic.main.activity_ricky_and_morty_details.*
import kotlinx.android.synthetic.main.item_rickey_and_morty.view.*
import kotlinx.android.synthetic.main.new_toolbar.*

class RickyAndMortyDetailsActivity : AppCompatActivity() {
    private lateinit var characterItem: CharacterItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (intent.extras != null) {
            characterItem =
                intent?.getParcelableExtra(ApplicationConstant.CHARACTER_DATA) ?: CharacterItem()
        }
        setTheDetailsOfCharacter()
    }

    private fun setTheDetailsOfCharacter() {
        Glide.with(this@RickyAndMortyDetailsActivity)
            .load(characterItem.image)
            .placeholder(R.drawable.ic_photo)
            .error(R.drawable.ic_photo)
            .into(image_view_thumbnail)

        if (TextUtils.isEmpty(characterItem.name).not()) {
            textViewName.text = characterItem.name
        }

        textViewGenderValue.text = characterItem.gender
        textViewLocationValue.text = characterItem.location?.name
        textViewStatusValue.text = characterItem.status
        textViewTypeValue.text = characterItem.type
        textViewSpeciesValue.text = characterItem.species
        textViewOriginValue.text = characterItem.origin?.name
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                super.onBackPressed()
        }
        return true
    }
}