package com.example.luthfihariz.footballclub.common.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers.mainThread
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {
    override fun computation(): Scheduler = Schedulers.computation()

    override fun io(): Scheduler = Schedulers.io()

    override fun ui(): Scheduler = mainThread()
}