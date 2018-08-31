package com.example.luthfihariz.footballclub.common.rx

import io.reactivex.Scheduler

interface BaseSchedulerProvider {

    fun computation(): Scheduler
    fun io(): Scheduler
    fun ui(): Scheduler
}