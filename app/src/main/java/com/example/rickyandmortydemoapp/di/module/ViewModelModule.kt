package com.example.rickyandmortydemoapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickyandmortydemoapp.activity.RickyAndMortyListingActivity
import com.example.rickyandmortydemoapp.viewmodel.FfdViewModelFactory
import com.example.rickyandmortydemoapp.viewmodel.RickyAndMortyViewModel
import com.example.rickyandmortydemoapp.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RickyAndMortyViewModel::class)
    abstract fun bindRickyAndMortyViewModel(viewModel: RickyAndMortyViewModel): ViewModel

    //@Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: FfdViewModelFactory): ViewModelProvider.Factory
}
