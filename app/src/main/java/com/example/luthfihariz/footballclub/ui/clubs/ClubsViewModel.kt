package com.example.luthfihariz.footballclub.ui.clubs

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.League
import com.example.luthfihariz.footballclub.data.repository.FootballClubDataSource
import com.example.luthfihariz.footballclub.data.repository.FootballLeagueDataSource
import io.reactivex.rxkotlin.subscribeBy

class ClubsViewModel(private val repository: FootballClubDataSource,
                     private val leagueRepository: FootballLeagueDataSource,
                     private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    var leagues: List<League> = ArrayList()
    val selectedLeague = MutableLiveData<League>()
    val clubsResource = MutableLiveData<Resource<List<Club>>>()

    fun getClubsByLeague() {
        clubsResource.postValue(Resource.loading())
        leagueRepository.getLeague()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            leagues = it
                            selectedLeague.value = it[0]
                            getClubs()
                        }
                )
    }

    fun getClubs() {
        selectedLeague.value?.let {
            clubsResource.postValue(Resource.loading())
            repository.getClubs(it.id)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeBy(
                            onNext = {
                                clubsResource.postValue(Resource.success(it))
                            },
                            onError = {
                                clubsResource.postValue(Resource.error(it))
                            }
                    )
        }

    }
}