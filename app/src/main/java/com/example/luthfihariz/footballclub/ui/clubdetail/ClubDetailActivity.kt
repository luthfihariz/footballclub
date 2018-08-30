package com.example.luthfihariz.footballclub.ui.clubdetail

import android.os.Bundle
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.data.model.FootballClub
import org.jetbrains.anko.*

class ClubDetailActivity : BaseActivity() {

    lateinit var ivClubLogo: ImageView
    lateinit var tvClubName: TextView
    lateinit var tvClubDesc: TextView

    companion object {
        const val ARG_FOOTBALL_CLUB = "footballClub"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constructView()
        bindData()
    }

    private fun bindData() {
        val footballClub = intent.getParcelableExtra<FootballClub>(ARG_FOOTBALL_CLUB)
        ivClubLogo.loadImageUrl(footballClub.logoUrl)
        tvClubName.text = footballClub.name
        tvClubDesc.text = footballClub.description
    }

    private fun constructView() {
        verticalLayout {
            lparams(width = matchParent, height = matchParent)
            gravity = Gravity.CENTER_HORIZONTAL
            padding = dip(16)

            ivClubLogo = imageView().lparams(width = dip(80), height = dip(80))

            tvClubName = textView {
                textSize = 16f
            }.lparams {
                topMargin = dip(8)
            }

            tvClubDesc = textView {
                textSize = 14f
            }.lparams {
                topMargin = dip(16)
            }
        }
    }

}
