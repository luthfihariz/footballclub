package com.example.luthfihariz.footballclub.di

import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.data.repository.FootballMatchRepository
import org.koin.dsl.module.applicationContext

val repositoryModule = applicationContext {

    bean { FootballMatchRepository(get(), get()) as FootballMatchDataSource }
}