package com.example.rickyandmortydemoapp.activity

import android.os.Bundle
import android.text.TextUtils
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.utils.ApplicationConstant
import kotlinx.android.synthetic.main.activity_ricky_and_morty_details.*
import kotlinx.android.synthetic.main.new_toolbar.*

class RickyAndMortyDetailsActivity : AppCompatActivity() {
    private lateinit var characterItem: CharacterItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (intent.extras != null && intent.hasExtra(ApplicationConstant.CHARACTER_DATA)) {
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
        } else {
            textViewName.text = getString(R.string.empty_value)
        }

        if (TextUtils.isEmpty(characterItem.gender).not()) {
            textViewGenderValue.text = characterItem.gender
        } else {
            textViewGenderValue.text = getString(R.string.empty_value)
        }

        if (characterItem.location != null && TextUtils.isEmpty(characterItem.location?.name)
                .not()
        ) {
            textViewLocationValue.text = characterItem.location?.name
        } else {
            textViewLocationValue.text = getString(R.string.empty_value)
        }

        if (TextUtils.isEmpty(characterItem.status).not()) {
            textViewStatusValue.text = characterItem.status
        } else {
            textViewStatusValue.text = getString(R.string.empty_value)
        }

        if (TextUtils.isEmpty(characterItem.type).not()) {
            textViewTypeValue.text = characterItem.type
        } else {
            textViewTypeValue.text = getString(R.string.empty_value)
        }

        if (TextUtils.isEmpty(characterItem.species).not()) {
            textViewSpeciesValue.text = characterItem.species
        } else {
            textViewSpeciesValue.text = getString(R.string.empty_value)
        }

        if (characterItem.origin != null && TextUtils.isEmpty(characterItem.origin?.name).not()) {
            textViewOriginValue.text = characterItem.origin?.name
        } else {
            textViewOriginValue.text = getString(R.string.empty_value)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                super.onBackPressed()
        }
        return true
    }
}