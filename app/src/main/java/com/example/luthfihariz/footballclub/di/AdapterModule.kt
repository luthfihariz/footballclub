package com.example.luthfihariz.footballclub.di

import com.example.luthfihariz.footballclub.ui.clubs.ClubsAdapter
import com.example.luthfihariz.footballclub.ui.matches.MatchesAdapter
import org.koin.dsl.module.applicationContext

val adapterModule = applicationContext {

    bean { MatchesAdapter() }
    bean { ClubsAdapter() }
}