package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.Player
import io.reactivex.Observable

interface FootballClubDataSource {

    fun getClubs(leagueId: String): Observable<List<Club>>

    fun getPlayers(clubId: String): Observable<List<Player>>

    fun searchClubs(query: String): Observable<List<Club>>

    fun getFavoriteClubs(): Observable<List<Club>>

    fun setFavorite(club: Club): Observable<Long>

    fun isFavorite(clubId: String): Observable<Boolean>

    fun removeFromFavorite(clubId: String): Observable<Int>

}
