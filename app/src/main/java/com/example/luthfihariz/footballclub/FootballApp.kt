package com.example.luthfihariz.footballclub

import android.app.Application
import com.example.luthfihariz.footballclub.di.appModule
import com.example.luthfihariz.footballclub.di.networkModule
import com.example.luthfihariz.footballclub.di.repositoryModule
import com.example.luthfihariz.footballclub.di.viewModelModule
import org.koin.android.ext.android.startKoin

class FootballApp : Application(){

    override fun onCreate() {
        super.onCreate()

        startKoin(listOf(appModule, networkModule, repositoryModule, viewModelModule))
    }

}
