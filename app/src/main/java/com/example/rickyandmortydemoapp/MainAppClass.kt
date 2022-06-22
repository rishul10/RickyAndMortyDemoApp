package com.example.rickyandmortydemoapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Application
import android.content.Context
import com.example.rickyandmortydemoapp.di.module.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class MainAppClass : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    companion object {
        @SuppressLint("StaticFieldLeak")
        var context: Context? = null
        var auth_token: String = ""
    }

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(this)
        context = this
    }

    override fun activityInjector() = dispatchingAndroidInjector
}