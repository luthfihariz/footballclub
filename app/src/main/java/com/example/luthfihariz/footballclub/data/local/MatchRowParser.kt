package com.example.luthfihariz.footballclub.data.local

import com.example.luthfihariz.footballclub.data.model.Match
import org.jetbrains.anko.db.RowParser

class MatchRowParser : RowParser<Match> {
    override fun parseRow(columns: Array<Any?>): Match {
        return Match(idEvent = columns[1] as String,
                strDate = columns[2] as String?,
                strHomeTeam = columns[3] as String,
                strAwayTeam = columns[4] as String,
                intHomeScore = (columns[5] as Long?)?.toInt(),
                intAwayScore = (columns[6] as Long?)?.toInt(),
                idAwayTeam = "",
                idHomeTeam = "")
    }
}
