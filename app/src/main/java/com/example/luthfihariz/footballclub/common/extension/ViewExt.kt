package com.example.luthfihariz.footballclub.common.extension

import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}


fun ViewGroup?.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this?.context).inflate(layoutRes, this, attachToRoot)
}