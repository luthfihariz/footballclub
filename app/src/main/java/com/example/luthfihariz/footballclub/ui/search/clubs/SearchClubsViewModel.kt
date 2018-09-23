package com.example.luthfihariz.footballclub.ui.search.clubs

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.luthfihariz.footballclub.common.rx.BaseSchedulerProvider
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.repository.FootballClubRepository
import io.reactivex.rxkotlin.subscribeBy

class SearchClubsViewModel(private val clubRepository: FootballClubRepository,
                             private val schedulerProvider: BaseSchedulerProvider) : ViewModel() {

    val clubs = MutableLiveData<List<Club>>()

    private fun searchClubs(query: String) {
        clubRepository.searchClubs(query)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeBy(
                        onNext = {
                            clubs.postValue(it)
                        }
                )
    }
}