package com.example.luthfihariz.footballclub.di

import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.common.rx.SchedulerProvider
import org.koin.dsl.module.applicationContext

val appModule = applicationContext {

    bean { SchedulerProvider() as BaseSchedulerProvider } // rx testing purpose
}