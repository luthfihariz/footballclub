package com.example.luthfihariz.footballclub.di

import com.example.luthfihariz.footballclub.data.repository.*
import org.koin.dsl.module.applicationContext

val repositoryModule = applicationContext {

    bean { FootballMatchRepository(get(), get()) as FootballMatchDataSource }
    bean { FootballLeagueRepository(get()) as FootballLeagueDataSource }
    bean { FootballClubRepository(get(), get()) as FootballClubDataSource }
}