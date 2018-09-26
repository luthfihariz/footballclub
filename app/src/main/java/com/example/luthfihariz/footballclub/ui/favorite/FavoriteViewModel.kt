package com.example.luthfihariz.footballclub.ui.favorite

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballClubDataSource
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import io.reactivex.rxkotlin.subscribeBy

class FavoriteViewModel(private val repository: FootballMatchDataSource,
                        private val clubRepository: FootballClubDataSource,
                        private val scheduler: BaseSchedulerProvider) : ViewModel() {

    val favoriteMatchesResource = MutableLiveData<Resource<List<Match>>>()
    val favoriteClubsResource = MutableLiveData<Resource<List<Club>>>()

    fun getFavoriteMatches() {
        favoriteMatchesResource.postValue(Resource.loading())
        repository.getFavoriteMatches()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribeBy(
                        onNext = {
                            favoriteMatchesResource.postValue(Resource.success(it))
                        },

                        onError = {
                            favoriteMatchesResource.postValue(Resource.error(it))
                        }
                )
    }

    fun getFavoriteClubs() {
        favoriteClubsResource.postValue(Resource.loading())
        clubRepository.getFavoriteClubs()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .subscribeBy(
                        onNext = {
                            favoriteClubsResource.postValue(Resource.success(it))
                        }
                )
    }

}