package com.example.luthfihariz.footballclub.data.repository

import android.database.sqlite.SQLiteConstraintException
import com.example.luthfihariz.footballclub.data.local.ClubRowParser
import com.example.luthfihariz.footballclub.data.local.DbRow
import com.example.luthfihariz.footballclub.data.local.DbTable
import com.example.luthfihariz.footballclub.data.local.FootballMatchDbHelper
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.Player
import com.example.luthfihariz.footballclub.data.remote.ApiService
import io.reactivex.Observable
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class FootballClubRepository(private val apiService: ApiService,
                             private val database: FootballMatchDbHelper) : FootballClubDataSource {

    override fun getClubs(leagueId: String): Observable<List<Club>> {
        return apiService.getTeamByLeague(leagueId).map {
            it.clubs ?: arrayListOf()
        }
    }

    override fun getPlayers(clubId: String): Observable<List<Player>> {
        return apiService.getPlayersByTeam(clubId).map {
            it.players
        }
    }

    override fun searchClubs(query: String): Observable<List<Club>> {
        return apiService.searchTeams(query).map {
            it.clubs ?: arrayListOf()
        }
    }

    override fun getFavoriteClubs(): Observable<List<Club>> {
        return Observable.create<List<Club>> {
            try {
                database.use {
                    val result = select(DbTable.FAVORITE_CLUB)
                    val favoriteClub = result.parseList(ClubRowParser())
                    it.onNext(favoriteClub)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }

        }
    }

    override fun setFavorite(club: Club): Observable<Long> {
        return Observable.create<Long> {
            try {
                database.use {
                    val insertedId = insert(DbTable.FAVORITE_CLUB,
                            DbRow.CLUB_ID to club.idTeam,
                            DbRow.CLUB_STADIUM to club.strStadium,
                            DbRow.CLUB_STADIUM_IMG to club.strStadiumThumb,
                            DbRow.CLUB_OVERVIEW to club.strDescriptionEN,
                            DbRow.CLUB_FORMED to club.intFormedYear,
                            DbRow.CLUB_LOGO to club.strTeamBadge,
                            DbRow.CLUB_NAME to club.strTeam)
                    it.onNext(insertedId)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }

    override fun isFavorite(clubId: String): Observable<Boolean> {
        return Observable.create<Boolean> {
            try {
                database.use {
                    val result = select(DbTable.FAVORITE_CLUB)
                            .whereSimple("(${DbRow.CLUB_ID} = ?)", clubId)
                    val clubs = result.parseList(ClubRowParser())
                    it.onNext(clubs.isNotEmpty())
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }

    override fun removeFromFavorite(clubId: String): Observable<Int> {
        return Observable.create<Int> {
            try {
                database.use {
                    val affectedRows = delete(DbTable.FAVORITE_CLUB, "(${DbRow.CLUB_ID} = {id})",
                            "id" to clubId)
                    it.onNext(affectedRows)
                }
            } catch (e: SQLiteConstraintException) {
                it.onError(e)
            }
        }
    }
}
