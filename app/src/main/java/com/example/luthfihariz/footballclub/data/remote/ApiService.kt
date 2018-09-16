package com.example.luthfihariz.footballclub.data.remote

import com.example.luthfihariz.footballclub.data.model.FootballLeagueResponse
import com.example.luthfihariz.footballclub.data.model.FootballMatchResponse
import com.example.luthfihariz.footballclub.data.model.FootballTeamResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("eventspastleague.php")
    fun getPrevMatches(@Query("id") leagueId: String): Observable<FootballMatchResponse>

    @GET("eventsnextleague.php")
    fun getNextMatches(@Query("id") leagueId: String): Observable<FootballMatchResponse>

    @GET("lookupevent.php")
    fun getMatchDetail(@Query("id") matchId: String): Observable<FootballMatchResponse>

    @GET("lookupteam.php")
    fun getTeamDetail(@Query("id") teamId: String): Observable<FootballTeamResponse>

    @GET("all_leagues.php")
    fun getLeagues(): Observable<FootballLeagueResponse>
}