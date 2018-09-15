package com.example.luthfihariz.footballclub.data.model

import com.google.gson.annotations.SerializedName

data class FootballLeagueResponse(val leagues: List<League>)

data class League(@SerializedName("idLeague") val id: String,
                  @SerializedName("strLeague") val name: String,
                  @SerializedName("strSport") val sport: String)