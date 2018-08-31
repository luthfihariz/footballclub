package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.remote.ApiService
import io.reactivex.Observable

class FootballMatchRepository(private val apiService: ApiService) : FootballMatchDataSource {

    companion object {
        const val LEAGUE_ID = 4328
    }

    override fun getPrevMatches(): Observable<List<Match>> = apiService.getPrevMatches(LEAGUE_ID).map {
        it.matches
    }

    override fun getNextMatches(): Observable<List<Match>> = apiService.getNextMatches(LEAGUE_ID).map {
        it.matches
    }
}