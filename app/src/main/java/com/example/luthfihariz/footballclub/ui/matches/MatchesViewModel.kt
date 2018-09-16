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
    val nextMatchResource = MutableLiveData<Resource<List<Match>>>()
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
                            getMatches(NEXT_MATCH)
                        }
                )
    }

    fun getMatches(type: Int) {
        when (type) {
            PREV_MATCH -> {
                getPrevMatches()
            }

            NEXT_MATCH -> {
                getNextMatches()
            }
        }
    }

    private fun getPrevMatches() {

    }

    private fun getNextMatches() {
        selectedLeague.value?.let {
            repository.getNextMatches(it.id)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeBy(
                            onNext = {
                                nextMatchResource.postValue(Resource.success(it))
                            },

                            onError = {
                                nextMatchResource.postValue(Resource.error(it))
                            }
                    )
        }

    }
}