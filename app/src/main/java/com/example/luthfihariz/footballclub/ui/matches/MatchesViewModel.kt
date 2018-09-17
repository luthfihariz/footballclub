package com.example.luthfihariz.footballclub.ui.matches

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.League
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballLeagueDataSource
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.NEXT_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import io.reactivex.rxkotlin.subscribeBy

class MatchesViewModel(private val repository: FootballMatchDataSource,
                       private val leagueRepository: FootballLeagueDataSource,
                       private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {


    val matchesResource = MutableLiveData<Resource<List<Match>>>()
    //val nextMatchResource = MutableLiveData<Resource<List<Match>>>()
    var leagues: List<League> = ArrayList()
    val selectedLeague = MutableLiveData<League>()

    fun getMatchByLeague() {
        matchesResource.postValue(Resource.loading())
        leagueRepository.getLeague()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            leagues = it
                            selectedLeague.value = it[0]
                            getMatches(NEXT_MATCH, it[0].id)
                        }
                )
    }

    fun getMatches(type: Int, leagueId: String) {
        matchesResource.postValue(Resource.loading())
        when (type) {
            PREV_MATCH -> {
                getPrevMatches(leagueId)
            }

            NEXT_MATCH -> {
                getNextMatches(leagueId)
            }
        }
    }

    private fun getPrevMatches(leagueId: String) {
        repository.getPrevMatches(leagueId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            matchesResource.postValue(Resource.success(it))
                        },

                        onError = {
                            matchesResource.postValue(Resource.error(it))
                        }
                )

    }

    private fun getNextMatches(leagueId: String) {
        repository.getNextMatches(leagueId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            matchesResource.postValue(Resource.success(it))
                        },

                        onError = {
                            matchesResource.postValue(Resource.error(it))
                        }
                )
    }
}