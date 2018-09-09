package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Match
import io.reactivex.Observable

interface FootballMatchDataSource{


    fun getPrevMatches() : Observable<List<Match>>

    fun getNextMatches() : Observable<List<Match>>

    fun getMatchDetail(id: String) : Observable<Match>

    fun setFavorite(match : Match) : Observable<Long> // return observable of new id

    fun removeFromFavorite(matchId : String) : Observable<Int> // return observable of affected rows

    fun isFavorite(matchId : String) : Observable<Boolean>

    fun getFavoriteMatches() : Observable<List<Match>>
}