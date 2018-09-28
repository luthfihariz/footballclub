package com.example.luthfihariz.footballclub.ui.clubsdetail

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.SingleLiveEvent
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.Player
import com.example.luthfihariz.footballclub.data.repository.FootballClubDataSource
import io.reactivex.rxkotlin.subscribeBy

class ClubDetailViewModel(private val repository: FootballClubDataSource,
                          private val schedulerProvider: BaseSchedulerProvider,
                          private val app: Application) : AndroidViewModel(app) {

    var club: Club? = null
    var players = MutableLiveData<List<Player>>()
    val favoriteState: MutableLiveData<Boolean> = MutableLiveData()
    val snackBarEvent: SingleLiveEvent<String> = SingleLiveEvent()


    fun getPlayers() {
        club?.let {
            repository.getPlayers(it.idTeam)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeBy(
                            onNext = {
                                players.postValue(it)
                            }
                    )
        }

    }

    fun toggleFavorite() {
        club?.let { club ->
            favoriteState.value?.let {
                favoriteState.postValue(!it)
                if (it) {
                    removeFromFavorite(club.idTeam)
                } else {
                    setFavorite(club)
                }
            }
        }
    }

    private fun setFavorite(club: Club) {
        repository.setFavorite(club)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onError = {
                            snackBarEvent.postValue("Error")
                        },
                        onNext = {
                            favoriteState.postValue(true)
                            snackBarEvent.postValue(app.getString(R.string.added_to_fav))
                        }
                )
    }

    private fun removeFromFavorite(id: String) {
        repository.removeFromFavorite(id)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onError = {
                            snackBarEvent.postValue("Error")
                        },
                        onNext = {
                            favoriteState.postValue(false)
                            snackBarEvent.postValue(app.getString(R.string.removed_from_fav))
                        }
                )
    }

    fun checkFavoriteState() {
        club?.let {
            repository.isFavorite(it.idTeam)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribeBy(
                            onError = {
                                snackBarEvent.postValue("Error")
                            },
                            onNext = {
                                favoriteState.postValue(it)
                            }
                    )
        }

    }

}