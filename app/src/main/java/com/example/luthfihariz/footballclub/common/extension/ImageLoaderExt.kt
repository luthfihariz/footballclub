package com.example.luthfihariz.footballclub.common.extension

import android.widget.ImageView
import com.squareup.picasso.Picasso

fun ImageView.loadImageUrl(imageUrl: String?) {
    imageUrl?.let {
        Picasso.with(context).load(imageUrl).into(this)
    }
}
