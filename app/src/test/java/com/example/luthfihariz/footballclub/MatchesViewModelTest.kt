package com.example.luthfihariz.footballclub

import android.accounts.NetworkErrorException
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.example.luthfihariz.footballclub.common.TestSchedulerProvider
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.repository.FootballMatchDataSource
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.NEXT_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class MatchesViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var matchViewModel: MatchesViewModel

    @Mock
    private lateinit var repository: FootballMatchDataSource
    @Mock
    private lateinit var observer: Observer<Resource<List<Match>>>

    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)
        matchViewModel = MatchesViewModel(repository, TestSchedulerProvider())
    }

    @Test
    fun testGetPrevMatchesSuccess() {
        Mockito.`when`(repository.getPrevMatches())
                .thenReturn(Observable.just(getMockedMatches()))

        matchViewModel.matchesResource.observeForever(observer)
        matchViewModel.getMatches(PREV_MATCH)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.success(getMockedMatches()))
    }

    @Test
    fun testGetPrevMatchesError() {
        val exception = NetworkErrorException()
        Mockito.`when`(repository.getPrevMatches())
                .thenReturn(Observable.error(exception))

        matchViewModel.matchesResource.observeForever(observer)
        matchViewModel.getMatches(PREV_MATCH)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.error(exception))
    }

    @Test
    fun testGetNextMatchesSuccess() {
        Mockito.`when`(repository.getNextMatches())
                .thenReturn(Observable.just(getMockedMatches()))

        matchViewModel.nextMatchResource.observeForever(observer)
        matchViewModel.getMatches(NEXT_MATCH)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.success(getMockedMatches()))
    }

    @Test
    fun testGetNextMatchesError() {
        val exception = NetworkErrorException()
        Mockito.`when`(repository.getNextMatches())
                .thenReturn(Observable.error(exception))

        matchViewModel.nextMatchResource.observeForever(observer)
        matchViewModel.getMatches(NEXT_MATCH)

        verify(observer).onChanged(Resource.loading())
        verify(observer).onChanged(Resource.error(exception))
    }

    private fun getMockedMatches(): List<Match> = mutableListOf()

}