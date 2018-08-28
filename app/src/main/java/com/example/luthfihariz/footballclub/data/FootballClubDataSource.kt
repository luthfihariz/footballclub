package com.example.luthfihariz.footballclub.data

import com.example.luthfihariz.footballclub.data.model.FootballClub

interface FootballClubDataSource {

    fun getClubs() : List<FootballClub>

}
