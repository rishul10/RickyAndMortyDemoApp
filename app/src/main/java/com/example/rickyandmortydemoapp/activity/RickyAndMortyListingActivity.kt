package com.example.rickyandmortydemoapp.activity

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.adapter.RickeyAndMortyListingAdapter
import com.example.rickyandmortydemoapp.base.BaseActivity
import com.example.rickyandmortydemoapp.model.CharacterItem
import com.example.rickyandmortydemoapp.utils.NetworkUtils
import com.example.rickyandmortydemoapp.viewmodel.RickyAndMortyViewModel
import kotlinx.android.synthetic.main.activity_ricky_and_morty_listing.*
import kotlinx.android.synthetic.main.include_empty_screen_layout.*
import kotlinx.android.synthetic.main.new_toolbar.*
import ru.alexbykov.nopaginate.callback.PaginateView
import ru.alexbykov.nopaginate.paginate.NoPaginate
import javax.inject.Inject

class RickyAndMortyListingActivity : BaseActivity(), PaginateView {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var noPaginate: NoPaginate
    private var pageCounter: Int = 0

    private lateinit var viewModel: RickyAndMortyViewModel

    private var characterList: ArrayList<CharacterItem> = arrayListOf()
    private lateinit var rickyAndMortyListingAdapter: RickeyAndMortyListingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ricky_and_morty_listing)

        viewModel =
            ViewModelProvider(this, viewModelFactory).get(RickyAndMortyViewModel::class.java)
        setupObservers()

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        if (NetworkUtils.isInternet(this)) {
            recyclerViewRickyMortyListing.visibility = View.VISIBLE
            initializeRecyclerView()
        } else {
            recyclerViewRickyMortyListing.visibility = View.GONE
            toast(getString(R.string.error_no_internet_connection))
        }

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
                pageCounter = 0
                apiCallForGettingList()
            }
            textViewClear.visibility = View.GONE
            viewSeparator.visibility = View.GONE
        }
    }

    private fun initializeRecyclerView() {
        recyclerViewRickyMortyListing?.apply {
            layoutManager = GridLayoutManager(this@RickyAndMortyListingActivity, 2)
            rickyAndMortyListingAdapter =
                RickeyAndMortyListingAdapter(this@RickyAndMortyListingActivity, characterList)
            adapter = rickyAndMortyListingAdapter
            initPagination() // call only after adapter set.
        }
    }

    private fun initPagination() {
        noPaginate = NoPaginate.with(recyclerViewRickyMortyListing)
            .setOnLoadMoreListener {
                showPaginateLoading(true)
                apiCallForGettingList()
            }
            .setLoadingTriggerThreshold(0)
            .build()
    }

    private fun apiCallForGettingList() {
        if (NetworkUtils.isInternet(this)) {
            pageCounter += 1
            viewModel.getCharacterList(pageCounter)
        } else {
            toast(getString(R.string.error_no_internet_connection))
            showPaginateLoading(false)
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
        viewModel.characterListResponse.observe(this) {
            if (it.results == null || (it.results.isEmpty() && pageCounter == 1)) {
                recyclerViewRickyMortyListing.visibility = View.GONE
                errorMessageView.visibility = View.VISIBLE
                errorMessageView.text = getString(R.string.no_data_found)
                setPaginateNoMoreData(true)

            } else {
                if (pageCounter == 1) {
                    errorMessageView.visibility = View.GONE
                    recyclerViewRickyMortyListing.visibility = View.VISIBLE

                    characterList.clear()
                    rickyAndMortyListingAdapter.notifyDataSetChanged()
                }
                for ((i, element) in it.results.withIndex()) {
                    if (element != null) {
                        characterList.add(element)
                    }
                    rickyAndMortyListingAdapter.notifyItemInserted(i)
                }

                if (it.info.count != null) {
                    if (characterList.size >= it.info.count) { // it means last page.
                        setPaginateNoMoreData(true)
                    } else {
                        setPaginateNoMoreData(false)
                    }
                }
            }
            showPaginateLoading(false)
        }

        viewModel.searchCharacterListResponse.observe(this@RickyAndMortyListingActivity) {
            if (it.results.size > 0) {
                characterList.clear()
                characterList.addAll(it.results)
                rickyAndMortyListingAdapter.notifyDataSetChanged()
            } else {
                recyclerViewRickyMortyListing.visibility = View.GONE
                search_bar.visibility = View.VISIBLE
                errorMessageView.visibility = View.VISIBLE
                errorMessageView.text = getString(R.string.no_data_found)
            }
        }
    }

    override fun onDestroy() {
        noPaginate.unbind()
        super.onDestroy()
    }

    override fun onBackPressed() {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Are you sure you want to close the application?")
        builder.setPositiveButton("Yes") { dialog, which ->
            dialog.dismiss()
            finish()
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

    override fun showPaginateError(show: Boolean) {
        noPaginate.showError(show)
    }

    override fun setPaginateNoMoreData(show: Boolean) {
        noPaginate.setNoMoreItems(show)
    }

    override fun showPaginateLoading(show: Boolean) {
        noPaginate.showLoading(show)
    }
}