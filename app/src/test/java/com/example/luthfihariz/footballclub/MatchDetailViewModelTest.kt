package com.example.luthfihariz.footballclub

import android.accounts.NetworkErrorException
import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.luthfihariz.footballclub.common.TestSchedulerProvider
import com.example.luthfihariz.footballclub.common.getTestObserver
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailViewModel
import io.reactivex.Observable
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.*
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class MatchDetailViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MatchDetailViewModel

    @Mock
    private lateinit var repository: FootballMatchDataSource
    @Mock
    private lateinit var context: Application
    @Mock
    private lateinit var match: Match

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        viewModel = MatchDetailViewModel(repository, TestSchedulerProvider(), context)
    }

    @Test
    fun testGetMatchDetailSuccess() {
        `when`(repository.getMatchDetail(anyString()))
                .thenReturn(Observable.just(match))

        val testMatchDetailResource = viewModel.matchDetailResource.getTestObserver()
        viewModel.getMatchDetail()

        assertEquals(testMatchDetailResource.observedValues,
                listOf(Resource.loading(), Resource.success(match)))
    }

    @Test
    fun testGetMatchDetailError() {
        val exception = NetworkErrorException()
        `when`(repository.getMatchDetail(anyString()))
                .thenReturn(Observable.error(exception))

        val testMatchDetailResource = viewModel.matchDetailResource.getTestObserver()
        viewModel.getMatchDetail()

        assertEquals(testMatchDetailResource.observedValues,
                listOf<Resource<Match>>(Resource.loading(),
                        Resource.error(exception)))
    }

    @Test
    fun testCheckFavoriteState() {
        `when`(repository.isFavorite(anyString()))
                .thenReturn(Observable.just(true))

        val testFavState = viewModel.favoriteState.getTestObserver()
        viewModel.checkFavoriteState()

        assertEquals(testFavState.observedValues, listOf(true))
    }


    @Test
    fun testSetAsFavorite() {
        `when`(repository.setFavorite(match))
                .thenReturn(Observable.just(anyLong()))

        val testFavoriteState = viewModel.favoriteState.getTestObserver()
        viewModel.favoriteState.postValue(false)
        viewModel.matchDetailResource.postValue(Resource.success(match))
        viewModel.toggleFavorite()

        assertEquals(testFavoriteState.observedValues, listOf(false, true, true))
    }

    @Test
    fun testRemoveFromFavorite() {
        `when`(repository.removeFromFavorite(anyString()))
                .thenReturn(Observable.just(anyInt()))

        val testFavoriteState = viewModel.favoriteState.getTestObserver()
        // precondition
        viewModel.favoriteState.postValue(true)
        viewModel.matchDetailResource.postValue(Resource.success(match))
        viewModel.toggleFavorite()

        assertEquals(testFavoriteState.observedValues, listOf(true, false, false))
    }
}
