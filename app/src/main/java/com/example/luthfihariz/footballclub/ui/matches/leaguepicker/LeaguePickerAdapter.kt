package com.example.luthfihariz.footballclub.ui.matches.leaguepicker

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.extension.gone
import com.example.luthfihariz.footballclub.common.extension.inflate
import com.example.luthfihariz.footballclub.common.extension.visible
import com.example.luthfihariz.footballclub.data.model.League
import kotlinx.android.synthetic.main.item_league_picker.view.*

class LeaguePickerAdapter : RecyclerView.Adapter<LeaguePickerAdapter.ItemViewHolder>() {

    var leagues: List<League> = ArrayList()
    var selectedId: String = ""
    lateinit var clickListener: (League) -> Unit


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ItemViewHolder {
        return ItemViewHolder(p0.inflate(R.layout.item_league_picker, false))
    }

    override fun getItemCount(): Int = leagues.size

    override fun onBindViewHolder(p0: ItemViewHolder, p1: Int) {
        p0.bind(leagues[p1])
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(league: League) {
            itemView.apply {
                tvLeagueName.text = league.name
                when (selectedId) {
                    league.id -> ivSelected.visible()
                    else -> ivSelected.gone()
                }
                setOnClickListener {
                    selectedId = league.id
                    clickListener(league)
                }
            }
        }
    }
}