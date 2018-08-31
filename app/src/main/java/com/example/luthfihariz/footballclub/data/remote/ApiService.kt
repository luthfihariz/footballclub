package com.example.luthfihariz.footballclub.data.remote

import com.example.luthfihariz.footballclub.data.model.FootballMatchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eventspastleague")
    fun getPrevMatches(@Query("id") leagueId: Int) : Observable<FootballMatchResponse>

    @GET("eventsnextleague")
    fun getNextMatches(@Query("id") leagueId: Int) : Observable<FootballMatchResponse>

    fun getEvent()
}