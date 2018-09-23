package com.example.luthfihariz.footballclub.data.remote

import com.example.luthfihariz.footballclub.data.model.*
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
    fun getTeamDetail(@Query("id") teamId: String): Observable<FootballClubResponse>

    @GET("lookup_all_teams.php")
    fun getTeamByLeague(@Query("id") leagueId: String): Observable<FootballClubResponse>

    @GET("lookup_all_players.php")
    fun getPlayersByTeam(@Query("id") teamId: String): Observable<FootballPlayerResponse>

    @GET("searchteams.php")
    fun searchTeams(@Query("t") query: String): Observable<FootballClubResponse>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String): Observable<FootballSearchMatchResponse>

    @GET("all_leagues.php")
    fun getLeagues(): Observable<FootballLeagueResponse>
}