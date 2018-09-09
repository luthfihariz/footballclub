package com.example.luthfihariz.footballclub.ui.matchdetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.SingleLiveEvent
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import io.reactivex.rxkotlin.subscribeBy

class MatchDetailViewModel(private val footballMatchRepository: FootballMatchDataSource,
                           private val schedulerProvider: BaseSchedulerProvider,
                           private val context: Application) : AndroidViewModel(context) {

    var matchId: String = ""
    val matchDetailResource: MutableLiveData<Resource<Match>> = MutableLiveData()
    val favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    val snackBarEvent: SingleLiveEvent<String> = SingleLiveEvent()

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

    fun toggleFavorite() {
        matchDetailResource.value?.data?.let { match ->
            favoriteState.value?.let {
                favoriteState.postValue(!it)
                if (it) {
                    removeFromFavorite()
                } else {
                    setFavorite(match)
                }
            }
        }
    }

    private fun setFavorite(match: Match) {
        footballMatchRepository.setFavorite(match)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onError = {
                        },
                        onNext = {
                            favoriteState.postValue(true)
                            snackBarEvent.postValue(context.getString(R.string.added_to_fav))
                        }
                )
    }

    private fun removeFromFavorite() {
        footballMatchRepository.removeFromFavorite(matchId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onError = {

                        },
                        onNext = {
                            favoriteState.postValue(false)
                            snackBarEvent.postValue(context.getString(R.string.removed_from_fav))
                        }
                )
    }

    fun checkFavoriteState() {
        footballMatchRepository.isFavorite(matchId)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onError = {

                        },
                        onNext = {
                            favoriteState.postValue(it)
                        }
                )
    }
}