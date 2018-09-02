package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Match
import io.reactivex.Observable

interface FootballMatchDataSource{


    fun getPrevMatches() : Observable<List<Match>>

    fun getNextMatches() : Observable<List<Match>>

    fun getMatchDetail(id: String) : Observable<Match>
}