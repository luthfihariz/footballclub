package com.example.luthfihariz.footballclub.ui.clubs

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.League

class ClubsViewModel : ViewModel() {

    var leagues: List<League> = ArrayList()
    val selectedLeague = MutableLiveData<League>()
    val clubsResource = MutableLiveData<Resource<List<Club>>>()

    fun getClubsByLeague() {

    }
}