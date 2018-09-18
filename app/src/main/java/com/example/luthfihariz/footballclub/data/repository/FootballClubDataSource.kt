package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.Club
import io.reactivex.Observable

interface FootballClubDataSource {

    fun getClubs(leagueId: String) : Observable<List<Club>>

}
