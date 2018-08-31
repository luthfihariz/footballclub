package com.example.luthfihariz.footballclub.ui.matches

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.data.repository.FootballMatchRepository
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.NEXT_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import io.reactivex.rxkotlin.subscribeBy

class MatchesViewModel(val repository: FootballMatchDataSource,
                       val schedulerProvider: BaseSchedulerProvider) : ViewModel() {


    val matchesResource = MutableLiveData<List<Match>>()

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

    fun getPrevMatches() {
        repository.getPrevMatches()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            matchesResource.postValue(it)
                        },

                        onError = {

                        }
                )
    }

    fun getNextMatches() {
        repository.getNextMatches()
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            matchesResource.postValue(it)
                        },

                        onError = {

                        }
                )
    }
}