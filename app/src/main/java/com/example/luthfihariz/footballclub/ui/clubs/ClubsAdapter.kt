package com.example.luthfihariz.footballclub.ui.clubs

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.extension.inflate
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.data.model.Club
import kotlinx.android.synthetic.main.item_club.view.*

class ClubsAdapter : RecyclerView.Adapter<ClubsAdapter.ClubsViewHolder>() {

    var clubs: List<Club> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickListener: (Club) -> Unit = {}

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ClubsViewHolder {
        return ClubsViewHolder(p0.inflate(R.layout.item_club, false))
    }

    override fun getItemCount(): Int = clubs.size

    override fun onBindViewHolder(p0: ClubsViewHolder, p1: Int) {
        p0.bind(clubs[p1])
    }

    inner class ClubsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(club: Club) {
            itemView.tvClubName.text = club.strTeam
            itemView.ivClubLogo.loadImageUrl(club.strTeamBadge)
            itemView.setOnClickListener {
                clickListener(club)
            }
        }
    }
}