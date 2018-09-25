package com.example.luthfihariz.footballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.luthfihariz.footballclub.data.local.DbConstant.DB_NAME
import com.example.luthfihariz.footballclub.data.local.DbConstant.DB_VERSION
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_FORMED
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_ID
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_LOGO
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_NAME
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_OVERVIEW
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_STADIUM
import com.example.luthfihariz.footballclub.data.local.DbRow.CLUB_STADIUM_IMG
import com.example.luthfihariz.footballclub.data.local.DbRow.ID
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_AWAY_SCORE
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_AWAY_TEAM
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_DATE
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_HOME_SCORE
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_HOME_TEAM
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_ID
import com.example.luthfihariz.footballclub.data.local.DbTable.FAVORITE_CLUB
import com.example.luthfihariz.footballclub.data.local.DbTable.FAVORITE_MATCH
import org.jetbrains.anko.db.*

object DbConstant {
    const val DB_NAME = "FootballMatch.db"
    const val DB_VERSION = 2
}

object DbTable {
    const val FAVORITE_MATCH = "favoriteMatch"
    const val FAVORITE_CLUB = "favoriteClub"
}

object DbRow {
    const val ID = "id"
    const val MATCH_ID = "matchId"
    const val MATCH_DATE = "date"
    const val MATCH_HOME_TEAM = "homeTeam"
    const val MATCH_AWAY_TEAM = "awayTeam"
    const val MATCH_HOME_SCORE = "homeScore"
    const val MATCH_AWAY_SCORE = "awayScore"

    const val CLUB_ID = "clubId"
    const val CLUB_LOGO = "clubLogo"
    const val CLUB_NAME = "clubName"
    const val CLUB_STADIUM = "clubStadium"
    const val CLUB_FORMED = "clubFormed"
    const val CLUB_STADIUM_IMG = "clubStadiumImg"
    const val CLUB_OVERVIEW = "clubOverview"
}


class FootballMatchDbHelper(context: Context) : ManagedSQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private var instance: FootballMatchDbHelper? = null

        @Synchronized
        fun getInstance(context: Context): FootballMatchDbHelper {
            if (instance == null) {
                instance = FootballMatchDbHelper(context.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.run {
            createTable(FAVORITE_MATCH, true,
                    ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    MATCH_ID to TEXT + UNIQUE,
                    MATCH_DATE to TEXT,
                    MATCH_HOME_TEAM to TEXT,
                    MATCH_AWAY_TEAM to TEXT,
                    MATCH_HOME_SCORE to INTEGER,
                    MATCH_AWAY_SCORE to INTEGER)

            createTable(FAVORITE_CLUB, true,
                    ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                    CLUB_ID to TEXT + UNIQUE,
                    CLUB_LOGO to TEXT,
                    CLUB_NAME to TEXT,
                    CLUB_STADIUM to TEXT,
                    CLUB_FORMED to TEXT,
                    CLUB_STADIUM_IMG to TEXT,
                    CLUB_OVERVIEW to TEXT)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.run {
            dropTable(FAVORITE_MATCH, true)
            dropTable(FAVORITE_CLUB, true)
        }
    }


}