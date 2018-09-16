package com.example.luthfihariz.footballclub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FootballLeagueResponse(val leagues: List<League>) : Parcelable

@Parcelize
data class League(@SerializedName("idLeague") val id: String,
                  @SerializedName("strLeague") val name: String,
                  @SerializedName("strSport") val sport: String) : Parcelable