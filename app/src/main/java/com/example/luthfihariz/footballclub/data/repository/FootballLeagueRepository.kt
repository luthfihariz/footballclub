package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.League
import com.example.luthfihariz.footballclub.data.remote.ApiService
import io.reactivex.Observable

class FootballLeagueRepository(private val apiService: ApiService) : FootballLeagueDataSource {


    override fun getLeague(): Observable<List<League>> {
        return apiService.getLeagues().map {
            it.leagues.filter {
                it.sport.equals("soccer", true)
            }
        }
    }
}