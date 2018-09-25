package com.example.luthfihariz.footballclub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FootballClubResponse(
        @SerializedName("teams") val clubs: List<Club>?
)

@Parcelize
data class Club(
        val idTeam: String,
        val strTeam: String,
        val intFormedYear: String,
        val strStadium: String,
        val strStadiumThumb: String,
        val strDescriptionEN: String?,
        val strTeamBadge: String
) : Parcelable


