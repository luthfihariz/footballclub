package com.example.luthfihariz.footballclub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FootballClubResponse(
        @SerializedName("teams") val clubs: List<Club>
)

@Parcelize
data class Club(
        val idTeam: String,
        val idSoccerXML: String,
        val intLoved: String?,
        val strTeam: String,
        val strTeamShort: String?,
        val strAlternate: String?,
        val intFormedYear: String,
        val strSport: String,
        val strLeague: String,
        val idLeague: String,
        val strDivision: String?,
        val strManager: String,
        val strStadium: String,
        val strKeywords: String,
        val strRSS: String,
        val strStadiumThumb: String,
        val strStadiumDescription: String,
        val strStadiumLocation: String,
        val intStadiumCapacity: String,
        val strWebsite: String,
        val strFacebook: String,
        val strTwitter: String,
        val strInstagram: String,
        val strDescriptionEN: String?,
        val strDescriptionDE: String?,
        val strDescriptionFR: String?,
        val strDescriptionCN: String?,
        val strDescriptionIT: String?,
        val strDescriptionJP: String?,
        val strDescriptionRU: String?,
        val strDescriptionES: String?,
        val strDescriptionPT: String?,
        val strDescriptionSE: String?,
        val strDescriptionNL: String?,
        val strDescriptionHU: String?,
        val strDescriptionNO: String?,
        val strDescriptionIL: String?,
        val strDescriptionPL: String?,
        val strGender: String,
        val strCountry: String,
        val strTeamBadge: String,
        val strTeamJersey: String,
        val strTeamLogo: String,
        val strTeamFanart1: String,
        val strTeamFanart2: String,
        val strTeamFanart3: String,
        val strTeamFanart4: String,
        val strTeamBanner: String,
        val strYoutube: String,
        val strLocked: String
) : Parcelable


