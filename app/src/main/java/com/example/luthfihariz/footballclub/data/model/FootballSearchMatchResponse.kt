package com.example.luthfihariz.footballclub.data.model

import com.google.gson.annotations.SerializedName

data class FootballSearchMatchResponse(
        @SerializedName("event") val matches: List<Match>?
)