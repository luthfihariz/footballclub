package com.example.luthfihariz.footballclub.data.model

import com.google.gson.annotations.SerializedName

data class FootballPlayerResponse(
        @SerializedName("player") val players: List<Player>
)

data class Player(
        val idPlayer: String,
        val idTeam: String,
        val idSoccerXML: String,
        val idPlayerManager: String,
        val strNationality: String,
        val strPlayer: String,
        val strTeam: String,
        val strSport: String,
        val intSoccerXMLTeamID: String,
        val dateBorn: String,
        val dateSigned: String,
        val strSigning: String,
        val strWage: String,
        val strBirthLocation: String,
        val strDescriptionEN: String,
        val strDescriptionDE: Any,
        val strDescriptionFR: Any,
        val strDescriptionCN: Any,
        val strDescriptionIT: Any,
        val strDescriptionJP: Any,
        val strDescriptionRU: Any,
        val strDescriptionES: Any,
        val strDescriptionPT: Any,
        val strDescriptionSE: Any,
        val strDescriptionNL: Any,
        val strDescriptionHU: Any,
        val strDescriptionNO: Any,
        val strDescriptionIL: Any,
        val strDescriptionPL: Any,
        val strGender: String,
        val strPosition: String,
        val strCollege: Any,
        val strFacebook: String,
        val strWebsite: String,
        val strTwitter: String,
        val strInstagram: String,
        val strYoutube: String,
        val strHeight: String,
        val strWeight: String,
        val intLoved: String,
        val strThumb: String,
        val strCutout: String,
        val strBanner: Any,
        val strFanart1: String,
        val strFanart2: String,
        val strFanart3: String,
        val strFanart4: String,
        val strLocked: String
)