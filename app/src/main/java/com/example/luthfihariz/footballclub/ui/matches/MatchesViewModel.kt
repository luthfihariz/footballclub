package com.example.luthfihariz.footballclub.ui.matches

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.NEXT_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import io.reactivex.rxkotlin.subscribeBy

class MatchesViewModel(private val repository: FootballMatchDataSource,
                       private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {


    val matchesResource = MutableLiveData<Resource<List<Match>>>()
    val nextMatchResource = MutableLiveData<Resource<List<Match>>>()

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
        matchesResource.postValue(Resource.loading())
        repository.getPrevMatches()
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

    private fun getNextMatches() {
        nextMatchResource.postValue(Resource.loading())
        repository.getNextMatches()
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