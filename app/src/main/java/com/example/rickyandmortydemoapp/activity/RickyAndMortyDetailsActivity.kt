package com.example.rickyandmortydemoapp.activity

import android.os.Bundle
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.base.BaseActivity
import kotlinx.android.synthetic.main.new_toolbar.*

class RickyAndMortyDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_details)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
    }

    override fun setupObservers() {
    }
}