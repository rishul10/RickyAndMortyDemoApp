package com.example.rickyandmortydemoapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickyandmortydemoapp.viewmodel.FfdViewModelFactory
import com.example.rickyandmortydemoapp.viewmodel.LoginViewModel
import com.example.rickyandmortydemoapp.viewmodel.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Suppress("unused")
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    //@Singleton
    @Binds
    abstract fun bindViewModelFactory(factory: FfdViewModelFactory): ViewModelProvider.Factory
}
