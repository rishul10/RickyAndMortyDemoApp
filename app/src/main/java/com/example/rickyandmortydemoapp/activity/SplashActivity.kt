package com.example.rickyandmortydemoapp.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.ViewModelProvider
import com.example.rickyandmortydemoapp.R
import com.example.rickyandmortydemoapp.base.BaseActivity
import com.example.rickyandmortydemoapp.utils.ApplicationConstant.SPLASH_TIME_OUT
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler(mainLooper).postDelayed({
            startActivity(Intent(this@SplashActivity, RickyAndMortyListingActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    override fun setupObservers() {

    }
}