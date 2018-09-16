package com.example.luthfihariz.footballclub.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.luthfihariz.footballclub.data.local.DbRow
import com.example.luthfihariz.footballclub.data.local.DbTable
import com.example.luthfihariz.footballclub.data.local.FootballMatchDbHelper
import com.example.luthfihariz.footballclub.data.local.MatchRowParser
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.data.remote.ApiService
import io.reactivex.Observable
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FootballMatchRepository(private val apiService: ApiService,
                              private val database: FootballMatchDbHelper) : FootballMatchDataSource {


    override fun getPrevMatches(leagueId: String): Observable<List<Match>> = apiService.getPrevMatches(leagueId).map {
        it.matches
    }

    override fun getNextMatches(leagueId: String): Observable<List<Match>> = apiService.getNextMatches(leagueId).map {
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
                            DbRow.MATCH_HOME_TEAM to match.strHomeTeam,
                            DbRow.MATCH_AWAY_TEAM to match.strAwayTeam,
                            DbRow.MATCH_HOME_SCORE to match.intHomeScore,
                            DbRow.MATCH_AWAY_SCORE to match.intAwayScore)
                    it.onNext(insertedId)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }

    override fun removeFromFavorite(matchId: String): Observable<Int> {
        return Observable.create<Int> {
            try {
                database.use {
                    val affectedRows = delete(DbTable.FAVORITED_MATCH, "(${DbRow.MATCH_ID} = {id})",
                            "id" to matchId)
                    it.onNext(affectedRows)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }

    }

    override fun isFavorite(matchId: String): Observable<Boolean> {
        return Observable.create<Boolean> {
            try {
                database.use {
                    val result = select(DbTable.FAVORITED_MATCH)
                            .whereSimple("(${DbRow.MATCH_ID} = ?)", matchId)
                    val matches = result.parseList(MatchRowParser())
                    it.onNext(matches.isNotEmpty())
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }

    override fun getFavoriteMatches(): Observable<List<Match>> {
        return Observable.create<List<Match>> {
            try {
                database.use {
                    val result = select(DbTable.FAVORITED_MATCH)
                    val favoritedMatches = result.parseList(MatchRowParser())
                    it.onNext(favoritedMatches)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }
}