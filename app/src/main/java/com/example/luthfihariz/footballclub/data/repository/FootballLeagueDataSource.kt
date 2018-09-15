package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.League
import io.reactivex.Observable

interface FootballLeagueDataSource {

    fun getLeague() : Observable<List<League>>
}