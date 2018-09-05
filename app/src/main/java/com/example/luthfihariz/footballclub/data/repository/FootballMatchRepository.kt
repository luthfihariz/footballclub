package com.example.luthfihariz.footballclub.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.luthfihariz.footballclub.data.local.DbRow
import com.example.luthfihariz.footballclub.data.local.DbTable
import com.example.luthfihariz.footballclub.data.local.FootballMatchDbHelper
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.remote.ApiService
import io.reactivex.Observable
import org.jetbrains.anko.db.insert

class FootballMatchRepository(private val apiService: ApiService,
                              private val database: FootballMatchDbHelper) : FootballMatchDataSource {

    companion object {
        const val LEAGUE_ID = 4328
    }

    override fun getPrevMatches(): Observable<List<Match>> = apiService.getPrevMatches(LEAGUE_ID).map {
        it.matches
    }

    override fun getNextMatches(): Observable<List<Match>> = apiService.getNextMatches(LEAGUE_ID).map {
        it.matches
    }


    override fun getMatchDetail(id: String): Observable<Match> {
        return apiService.getMatchDetail(id).flatMap {
            val match = it.matches[0]
            apiService.getTeamDetail(match.idHomeTeam).flatMap {
                match.homeTeam = it.teams[0]

                apiService.getTeamDetail(match.idAwayTeam).map {
                    match.awayTeam = it.teams[0]
                    match
                }
            }
        }
    }


    override fun setFavorite(match: Match): Observable<Long> {
        return Observable.create<Long> {
            try {
                database.use {
                    val insertedId = insert(DbTable.FAVORITED_MATCH,
                            DbRow.MATCH_ID to match.idEvent,
                            DbRow.MATCH_DATE to match.strDate,
                            DbRow.MATCH_HOME_TEAM to match.homeTeam,
                            DbRow.MATCH_AWAY_TEAM to match.awayTeam,
                            DbRow.MATCH_HOME_SCORE to match.intHomeScore,
                            DbRow.MATCH_AWAY_SCORE to match.intAwayScore)
                    it.onNext(insertedId)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }

    override fun removeFromFavorite(matchId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isFavorite(id: String): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFavoriteEvents(): Observable<List<Match>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}