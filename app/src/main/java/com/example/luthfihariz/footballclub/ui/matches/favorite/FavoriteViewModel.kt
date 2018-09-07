package com.example.luthfihariz.footballclub.ui.matches.favorite

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchRepository
import io.reactivex.rxkotlin.subscribeBy

class FavoriteViewModel(private val repository: FootballMatchRepository,
                        private val scheduler: BaseSchedulerProvider) : ViewModel() {

    val matchesResource = MutableLiveData<Resource<List<Match>>>()

    fun getFavoriteMatches() {
        repository.getFavoriteMatches()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribeBy(
                        onNext = {

                        },

                        onError = {

                        }
                )
    }

}