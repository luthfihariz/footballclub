package com.example.luthfihariz.footballclub

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import android.database.sqlite.SQLiteConstraintException
import com.example.luthfihariz.footballclub.common.TestSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.ui.matches.favorite.FavoriteViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class FavoriteViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var favViewModel: FavoriteViewModel

    @Mock
    private lateinit var repository: FootballMatchDataSource
    @Mock
    private lateinit var observer: Observer<Resource<List<Match>>>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        favViewModel = FavoriteViewModel(repository, TestSchedulerProvider())
    }

    @Test
    fun testGetFavoriteMatchesSuccess() {
        Mockito.`when`(repository.getFavoriteMatches())
                .thenReturn(Observable.just(getMockedMatches()))

        favViewModel.favoriteMatchesResource.observeForever(observer)
        favViewModel.getFavoriteMatches()

        Mockito.verify(observer).onChanged(Resource.loading())
        Mockito.verify(observer).onChanged(Resource.success(getMockedMatches()))
    }

    @Test
    fun testGetFavoriteMatchesError() {
        val exception = SQLiteConstraintException()
        Mockito.`when`(repository.getFavoriteMatches())
                .thenReturn(Observable.error(exception))

        favViewModel.favoriteMatchesResource.observeForever(observer)
        favViewModel.getFavoriteMatches()

        Mockito.verify(observer).onChanged(Resource.loading())
        Mockito.verify(observer).onChanged(Resource.error(exception))
    }

    private fun getMockedMatches(): List<Match> = mutableListOf()

}