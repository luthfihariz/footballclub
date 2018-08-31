package com.example.luthfihariz.footballclub.data.repository

import com.example.luthfihariz.footballclub.data.model.FootballClub

interface FootballClubDataSource {

    fun getClubs() : List<FootballClub>

}
