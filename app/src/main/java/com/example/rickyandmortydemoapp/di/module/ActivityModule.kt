package com.example.rickyandmortydemoapp.di.module

import com.example.rickyandmortydemoapp.activity.RickyAndMortyListingActivity
import com.example.rickyandmortydemoapp.activity.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    //Register your app activities here

    @ContributesAndroidInjector
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun contributeRickyAndMortyActivity(): RickyAndMortyListingActivity
}