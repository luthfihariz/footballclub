package com.example.luthfihariz.footballclub.data.local

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.example.luthfihariz.footballclub.data.local.DbConstant.DB_NAME
import com.example.luthfihariz.footballclub.data.local.DbConstant.DB_VERSION
import com.example.luthfihariz.footballclub.data.local.DbRow.ID
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_AWAY_SCORE
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_AWAY_TEAM
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_DATE
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_HOME_SCORE
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_HOME_TEAM
import com.example.luthfihariz.footballclub.data.local.DbRow.MATCH_ID
import com.example.luthfihariz.footballclub.data.local.DbTable.FAVORITED_MATCH
import org.jetbrains.anko.db.*

object DbConstant {
    const val DB_NAME = "FootballMatch.db"
    const val DB_VERSION = 1
}

object DbTable {
    const val FAVORITED_MATCH = "favoritedMatch"
}

object DbRow {
    const val ID = "id"
    const val MATCH_ID = "matchId"
    const val MATCH_DATE = "date"
    const val MATCH_HOME_TEAM = "homeTeam"
    const val MATCH_AWAY_TEAM = "awayTeam"
    const val MATCH_HOME_SCORE = "homeScore"
    const val MATCH_AWAY_SCORE = "awayScore"
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
        db?.createTable(FAVORITED_MATCH, true,
                ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                MATCH_ID to TEXT + UNIQUE,
                MATCH_DATE to TEXT,
                MATCH_HOME_TEAM to TEXT,
                MATCH_AWAY_TEAM to TEXT,
                MATCH_HOME_SCORE to INTEGER,
                MATCH_AWAY_SCORE to INTEGER)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FAVORITED_MATCH, true)
    }


}