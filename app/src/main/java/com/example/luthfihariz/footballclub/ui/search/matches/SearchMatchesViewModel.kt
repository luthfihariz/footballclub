package com.example.luthfihariz.footballclub.ui.search.matches

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import io.reactivex.rxkotlin.subscribeBy

class SearchMatchesViewModel(private val matchRepository: FootballMatchDataSource,
                             private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    val matches = MutableLiveData<List<Match>>()

    fun searchMatches(query: String) {
        matchRepository.searchMatches(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            matches.postValue(it)
                        }
                )
    }
}