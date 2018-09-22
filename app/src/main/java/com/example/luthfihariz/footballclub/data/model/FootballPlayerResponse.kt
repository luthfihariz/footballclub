package com.example.luthfihariz.footballclub.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class FootballPlayerResponse(
        @SerializedName("player") val players: List<Player>
)

@Parcelize
data class Player(
        val idPlayer: String,
        val idTeam: String,
        val idSoccerXML: String,
        val idPlayerManager: String?,
        val strNationality: String,
        val strPlayer: String,
        val strTeam: String,
        val dateBorn: String?,
        val dateSigned: String?,
        val strSigning: String?,
        val strWage: String?,
        val strBirthLocation: String?,
        val strDescriptionEN: String,
        val strGender: String,
        val strPosition: String,
        val strCollege: String?,
        val strFacebook: String?,
        val strWebsite: String?,
        val strTwitter: String?,
        val strInstagram: String?,
        val strYoutube: String?,
        val strHeight: String?,
        val strWeight: String?,
        val intLoved: String?,
        val strThumb: String?,
        val strCutout: String?,
        val strBanner: String?,
        val strFanart1: String?
) : Parcelable