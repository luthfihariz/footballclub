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
        val idSoccerXML: String,
        val strEvent: String,
        val strFilename: String,
        val strSport: String,
        val idLeague: String,
        val strLeague: String,
        val strSeason: String,
        val strDescriptionEN: String?,
        val strHomeTeam: String,
        val strAwayTeam: String,
        val intHomeScore: Int?,
        val intRound: String,
        val intAwayScore: Int?,
        val intSpectators: Int?,
        val strHomeGoalDetails: String?,
        val strHomeRedCards: String?,
        val strHomeYellowCards: String?,
        val strHomeLineupGoalkeeper: String?,
        val strHomeLineupDefense: String?,
        val strHomeLineupMidfield: String?,
        val strHomeLineupForward: String?,
        val strHomeLineupSubstitutes: String?,
        val strHomeFormation: String?,
        val strAwayRedCards: String?,
        val strAwayYellowCards: String?,
        val strAwayGoalDetails: String?,
        val strAwayLineupGoalkeeper: String?,
        val strAwayLineupDefense: String?,
        val strAwayLineupMidfield: String?,
        val strAwayLineupForward: String?,
        val strAwayLineupSubstitutes: String?,
        val strAwayFormation: String?,
        val intHomeShots: Int?,
        val intAwayShots: Int?,
        val dateEvent: String,
        val strDate: String,
        val strTime: String,
        val strTVStation: String?,
        val idHomeTeam: String,
        val idAwayTeam: String,
        val strResult: String?,
        val strCircuit: String?,
        val strCountry: String?,
        val strCity: String?,
        val strPoster: String?,
        val strFanart: String?,
        val strThumb: String?,
        val strBanner: String?,
        val strMap: String?,
        val strLocked: String,
        var homeTeam: Team?,
        var awayTeam: Team?
) : Parcelable