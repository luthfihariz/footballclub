package com.example.luthfihariz.footballclub.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FootballClub(val logoUrl: String,
                        val name: String,
                        val description: String) : Parcelable