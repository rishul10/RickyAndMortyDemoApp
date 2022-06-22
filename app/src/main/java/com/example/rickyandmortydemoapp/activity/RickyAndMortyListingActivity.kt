package com.example.rickyandmortydemoapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rickyandmortydemoapp.R
import kotlinx.android.synthetic.main.new_toolbar.*

class RickyAndMortyListingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_listing)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

    }
}