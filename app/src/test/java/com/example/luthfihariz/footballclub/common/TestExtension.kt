package com.example.luthfihariz.footballclub.common

import android.arch.lifecycle.LiveData

fun <T> LiveData<T>.getTestObserver() = TestObserver<T>().apply {
    observeForever(this)
}