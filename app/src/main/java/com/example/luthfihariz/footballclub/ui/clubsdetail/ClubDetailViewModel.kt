package com.example.luthfihariz.footballclub.ui.clubsdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.model.Player
import com.example.luthfihariz.footballclub.data.repository.FootballClubDataSource
import com.example.luthfihariz.footballclub.data.repository.FootballLeagueDataSource
import io.reactivex.rxkotlin.subscribeBy

class ClubDetailViewModel(private val repository: FootballClubDataSource,
                          private val leagueRepository: FootballLeagueDataSource,
                          private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    var clubId = ""
    var players = MutableLiveData<List<Player>>()


    fun getPlayers() {
        repository.getPlayers(clubId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            players.postValue(it)
                        }
                )
    }


}