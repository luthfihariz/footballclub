package com.example.luthfihariz.footballclub.data.local

import com.example.luthfihariz.footballclub.data.model.Club
import org.jetbrains.anko.db.RowParser

class ClubRowParser : RowParser<Club> {
    override fun parseRow(columns: Array<Any?>): Club {
        return Club(idTeam = columns[1] as String,
                strTeamBadge = columns[2] as String,
                strTeam = columns[3] as String,
                strStadium = columns[4] as String,
                intFormedYear = columns[5] as String,
                strStadiumThumb = columns[6] as String,
                strDescriptionEN = columns[6] as String)
    }
}
