package com.example.rickyandmortydemoapp.di.component

import android.app.Application
import com.example.rickyandmortydemoapp.MainAppClass
import com.example.rickyandmortydemoapp.di.module.ActivityModule
import com.example.rickyandmortydemoapp.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, ActivityModule::class, AndroidInjectionModule::class])
interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MainAppClass)
}
