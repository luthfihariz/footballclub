package com.example.luthfihariz.footballclub.ui.matchdetail

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import io.reactivex.rxkotlin.subscribeBy

class MatchDetailViewModel(private val footballMatchRepository: FootballMatchDataSource,
                           private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    var matchId: String = ""

    val matchDetailResource: MutableLiveData<Resource<Match>> = MutableLiveData()

    fun getMatchDetail() {
        matchDetailResource.postValue(Resource.loading())
        footballMatchRepository.getMatchDetail(matchId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            matchDetailResource.postValue(Resource.success(it))
                        },

                        onError = {
                            matchDetailResource.postValue(Resource.error(it))
                        }
                )

    }
}