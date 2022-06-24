package com.example.rickyandmortydemoapp.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.adapter.RickyAndMortyListingAdapter
import com.example.rickyandmortydemoapp.base.BaseActivity
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.utils.NetworkUtils
import com.example.rickyandmortydemoapp.viewmodel.RickyAndMortyViewModel
import kotlinx.android.synthetic.main.activity_ricky_and_morty_listing.*
import kotlinx.android.synthetic.main.include_empty_screen_layout.*
import kotlinx.android.synthetic.main.new_toolbar.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


class RickyAndMortyListingActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: RickyAndMortyViewModel

    private var characterList: ArrayList<CharacterItem> = arrayListOf()
    private lateinit var mRickyAndMortyListingAdapter: RickyAndMortyListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_listing)

        viewModel = ViewModelProvider(this, viewModelFactory).get(RickyAndMortyViewModel::class.java)
        setupObservers()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

//        viewModel.getCharacterList(1, 10)

        searchBar.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                charSequence: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
            }

            override fun onTextChanged(
                charSequence: CharSequence,
                start: Int,
                before: Int,
                count: Int
            ) {
                if (charSequence.length >= 3) {
                    textViewClear.visibility = View.VISIBLE
                    viewSeparator.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(editable: Editable) {}
        })

        searchItemButton.setOnClickListener {
            apiToFetchSearchedData()
        }
        textViewClear.setOnClickListener {
            searchBar.setText("")
            if (NetworkUtils.isInternet(this@RickyAndMortyListingActivity)) {
                setupView()
            }
            textViewClear.visibility = View.GONE
            viewSeparator.visibility = View.GONE
        }

        recyclerViewRickyMortyListing.layoutManager = GridLayoutManager(this, 2)
        recyclerViewRickyMortyListing.isNestedScrollingEnabled = false
        mRickyAndMortyListingAdapter = RickyAndMortyListingAdapter()
        recyclerViewRickyMortyListing.adapter = mRickyAndMortyListingAdapter
//        recyclerViewRickyMortyListing.adapter = mRickyAndMortyListingAdapter.withLoadStateHeaderAndFooter(
//            header = CharacterLoadStateAdapter { mRickyAndMortyListingAdapter.retry() },
//            footer = CharacterLoadStateAdapter { mRickyAndMortyListingAdapter.retry() }
//        )
        recyclerViewRickyMortyListing.setHasFixedSize(true)

        setupView()
    }

    private fun setupView() {
        lifecycleScope.launch {
            viewModel.characterResponse.collectLatest { pagedData ->
                mRickyAndMortyListingAdapter.submitData(pagedData)
            }
        }
    }

    private fun apiToFetchSearchedData() {
        val searchData = searchBar.text.toString()
        if (TextUtils.isEmpty(searchData).not() && searchData.length >= 3) {
            if (NetworkUtils.isInternet(this)) {
                showActivitySpinner()
                viewModel.getSearchCharacterList(searchData)
            }
        } else {
            toast("Please enter at-least three characters to make a search request")
        }
    }

    override fun setupObservers() {
        observeProgressBarAndErrorLiveData(viewModel)
        viewModel.characterListResponse.observe(this@RickyAndMortyListingActivity) {
            if (it.results.size > 0) {
                characterList.clear()
                characterList.addAll(it.results)
                mRickyAndMortyListingAdapter.notifyDataSetChanged()
            } else {
                recyclerViewRickyMortyListing.visibility = View.GONE
                search_bar.visibility = View.VISIBLE
                errorMessageView.visibility = View.VISIBLE
                errorMessageView.text = "No data found"
            }
        }

        viewModel.searchCharacterListResponse.observe(this@RickyAndMortyListingActivity) {
            if (it.results.size > 0) {
                characterList.clear()
                characterList.addAll(it.results)
                mRickyAndMortyListingAdapter.notifyDataSetChanged()
            } else {
                recyclerViewRickyMortyListing.visibility = View.GONE
                search_bar.visibility = View.VISIBLE
                errorMessageView.visibility = View.VISIBLE
                errorMessageView.text = "No data found"
            }
        }
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to close the application?")
        builder.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            finish()
            finishAffinity();

        }

        builder.setNegativeButton("Cancel") { dialog, which ->
        dialog.dismiss()
        }

        val alertDialog = builder.create()
        alertDialog.show()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home ->
                onBackPressed()
        }
        return true
    }
}