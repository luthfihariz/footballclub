package com.example.luthfihariz.footballclub.ui.matches

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.extension.inflate
import com.example.luthfihariz.footballclub.data.model.Match

class MatchesAdapter : RecyclerView.Adapter<MatchesViewHolder>() {

    var matches: List<Match> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickListener: (Match) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MatchesViewHolder = MatchesViewHolder(parent.inflate(R.layout.item_football_match))

    override fun getItemCount(): Int = matches.size


    override fun onBindViewHolder(holder: MatchesViewHolder, p1: Int) {
        holder.bind(matches[p1], clickListener)
    }

}