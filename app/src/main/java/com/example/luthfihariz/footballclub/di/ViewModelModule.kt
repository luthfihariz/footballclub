package com.example.luthfihariz.footballclub.di

import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailViewModel
import com.example.luthfihariz.footballclub.ui.matches.MatchesViewModel
import org.koin.dsl.module.applicationContext

val viewModelModule = applicationContext {

    bean { MatchesViewModel(get(), get()) }
    bean { MatchDetailViewModel(get(), get()) }
}