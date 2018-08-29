package com.example.luthfihariz.footballclub.common.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.image

fun ImageView.loadImageUrl(imageUrl : String) {
    Picasso.with(context).load(imageUrl).into(this)
}