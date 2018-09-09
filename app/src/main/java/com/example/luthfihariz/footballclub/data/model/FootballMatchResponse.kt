package com.example.luthfihariz.footballclub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FootballMatchResponse(
        @SerializedName("events") val matches: List<Match>
)

@Parcelize
data class Match(
        val idEvent: String,
        val strHomeTeam: String,
        val strAwayTeam: String,
        val intHomeScore: Int?,
        val intAwayScore: Int?,
        val strHomeGoalDetails: String? = null,
        val strHomeLineupGoalkeeper: String? = null,
        val strHomeLineupDefense: String? = null,
        val strHomeLineupMidfield: String? = null,
        val strHomeLineupForward: String? = null ,
        val strHomeLineupSubstitutes: String? = null,
        val strHomeFormation: String? = null,
        val strAwayGoalDetails: String? = null,
        val strAwayLineupGoalkeeper: String? = null,
        val strAwayLineupDefense: String? = null,
        val strAwayLineupMidfield: String? = null,
        val strAwayLineupForward: String? = null,
        val strAwayLineupSubstitutes: String? = null,
        val strAwayFormation: String? = null,
        val intHomeShots: Int? = null,
        val intAwayShots: Int? = null,
        val strDate: String,
        val idHomeTeam: String,
        val idAwayTeam: String,
        var homeTeam: Team? = null,
        var awayTeam: Team? = null
) : Parcelable