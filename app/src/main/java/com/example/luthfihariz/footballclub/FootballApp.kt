package com.example.luthfihariz.footballclub

import android.app.Application
import com.example.luthfihariz.footballclub.di.*
import com.facebook.stetho.Stetho
import org.koin.android.ext.android.startKoin

class FootballApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin(listOf(appModule, networkModule, repositoryModule, viewModelModule, adapterModule))

        Stetho.initializeWithDefaults(this)
    }

}
