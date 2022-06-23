package com.example.rickyandmortydemoapp.activity

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.adapter.RickeyAndMortyListingAdapter
import com.example.rickyandmortydemoapp.base.BaseActivity
import com.example.rickyandmortydemoapp.di.module.AppInjector
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.viewmodel.RickyAndMortyViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_ricky_and_morty_listing.*
import kotlinx.android.synthetic.main.new_toolbar.*
import javax.inject.Inject

class RickyAndMortyListingActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RickyAndMortyViewModel // reused

    private var characterList: ArrayList<CharacterItem> = arrayListOf()
    private lateinit var adapter: RickeyAndMortyListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_listing)

        viewModel = ViewModelProvider(this, viewModelFactory).get(RickyAndMortyViewModel::class.java)
        setupObservers()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        viewModel.getCharacterList()

        recyclerViewRickyMortyListing.layoutManager = GridLayoutManager(this,2)
        recyclerViewRickyMortyListing.isNestedScrollingEnabled = false
        adapter = RickeyAndMortyListingAdapter(this@RickyAndMortyListingActivity, characterList)
        recyclerViewRickyMortyListing.adapter = adapter
    }

    override fun setupObservers() {
        observeProgressBarAndErrorLiveData(viewModel)
        viewModel.characterListResponse.observe(this@RickyAndMortyListingActivity) {
            characterList.add(it.results[0])
        }

    }
}