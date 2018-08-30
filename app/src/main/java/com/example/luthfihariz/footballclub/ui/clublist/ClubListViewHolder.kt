package com.example.luthfihariz.footballclub.ui.clublist

import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.data.model.FootballClub
import org.jetbrains.anko.*

class ClubListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(footballClub: FootballClub, clickListener : (FootballClub) -> Unit) {
        itemView.find<TextView>(R.id.tvClubName).text = footballClub.name
        itemView.find<ImageView>(R.id.ivClubLogo).loadImageUrl(footballClub.logoUrl)
        itemView.setOnClickListener {
            clickListener(footballClub)
        }

    }

    class ClubListItemUi : AnkoComponent<ViewGroup> {
        override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
            linearLayout {

                orientation = LinearLayout.HORIZONTAL
                gravity = Gravity.CENTER_VERTICAL

                lparams(width = matchParent, height = wrapContent) {
                    margin = dip(16)
                }

                imageView {
                    id = R.id.ivClubLogo
                }.lparams(width = dip(80), height = dip(80))

                textView {
                    id = R.id.tvClubName
                }.lparams(width = wrapContent, height = wrapContent) {
                    leftMargin = dip(12)
                }
            }
        }
    }

}