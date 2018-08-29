package com.example.luthfihariz.footballclub.ui.clublist

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.data.model.FootballClub
import org.jetbrains.anko.AnkoContext

class ClubListAdapter : RecyclerView.Adapter<ClubListViewHolder>() {


    var footballClubs: List<FootballClub> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ClubListViewHolder =
            ClubListViewHolder(ClubListViewHolder
                    .ClubListItemUi()
                    .createView(AnkoContext.create(parent.context, parent)))


    override fun getItemCount(): Int = footballClubs.size

    override fun onBindViewHolder(holder: ClubListViewHolder, pos: Int) {
        holder.bind(footballClubs[pos])
    }

}
