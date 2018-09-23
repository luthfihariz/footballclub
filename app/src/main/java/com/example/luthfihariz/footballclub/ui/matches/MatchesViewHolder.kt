package com.example.luthfihariz.footballclub.ui.matches

import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.luthfihariz.footballclub.data.model.Match
import com.luthfihariz.kocax.adjustTimePattern
import kotlinx.android.synthetic.main.item_football_match.view.*

class MatchesViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(match: Match, clickListener : (Match) -> Unit){
        itemView.apply{
            tvDate.text = match.strDate?.adjustTimePattern("dd/MM/yy", "EEE, dd MMM yyyy")
            tvTeamHome.text = match.strHomeTeam
            tvTeamAway.text = match.strAwayTeam

            match.intHomeScore?.let {
                tvScoreHome.text = it.toString()
            }

            match.intAwayScore?.let {
                tvScoreAway.text = it.toString()
            }

            setOnClickListener {
                clickListener(match)
            }
        }

    }
}