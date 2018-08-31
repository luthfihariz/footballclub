package com.example.luthfihariz.footballclub.data.remote

import com.example.luthfihariz.footballclub.data.model.FootballMatchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eventspastleague.php")
    fun getPrevMatches(@Query("id") leagueId: Int) : Observable<FootballMatchResponse>

    @GET("eventsnextleague.php")
    fun getNextMatches(@Query("id") leagueId: Int) : Observable<FootballMatchResponse>

    fun getEvent()
}