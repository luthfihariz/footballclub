package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Match
import io.reactivex.Observable

interface FootballMatchDataSource{


    fun getPrevMatches() : Observable<List<Match>>

    fun getNextMatches() : Observable<List<Match>>

    fun getMatchDetail(id: String) : Observable<Match>

    fun setFavorite(match : Match) : Observable<Long>

    fun removeFromFavorite(matchId : String)

    fun isFavorite(matchId : String) : Observable<Boolean>

    fun getFavoriteEvents() : Observable<List<Match>>
}