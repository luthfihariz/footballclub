package com.example.luthfihariz.footballclub.ui.clubsdetail

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.extension.inflate
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.data.model.Player
import kotlinx.android.synthetic.main.item_football_player.view.*

class ClubPlayersAdapter : RecyclerView.Adapter<ClubPlayersAdapter.PlayerViewHolder>() {

    var players: List<Player> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var clickListener: (Player) -> Unit = {}

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): PlayerViewHolder {
        return PlayerViewHolder(p0.inflate(R.layout.item_football_player, false))
    }

    override fun getItemCount(): Int = players.size

    override fun onBindViewHolder(p0: PlayerViewHolder, p1: Int) {
        p0.bind(players[p1])
    }

    inner class PlayerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(player: Player) {
            itemView.ivPlayerAvatar.loadImageUrl(player.strCutout)
            itemView.tvPlayerName.text = player.strPlayer
            itemView.setOnClickListener {
                clickListener(player)
            }
        }
    }


}