package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.remote.ApiService
import io.reactivex.Observable

class FootballClubRepository(private val apiService: ApiService) : FootballClubDataSource {

    override fun getClubs(leagueId: String): Observable<List<Club>> {
        return apiService.getTeamByLeague(leagueId).map {
            it.club
        }
    }
}