package com.example.luthfihariz.footballclub.ui.player

import android.os.Bundle
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.data.model.Player
import kotlinx.android.synthetic.main.activity_player_detail.*

class PlayerDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)
        bindData(intent.getParcelableExtra("player"))
    }

    private fun bindData(player: Player?) {
        player?.let {
            ivFanart.loadImageUrl(it.strFanart1)
            ivAvatar.loadImageUrl(it.strCutout)
            tvName.text = it.strPlayer
            tvPosition.text = it.strPosition
            tvBorn.text = it.dateBorn
            tvOverview.text = it.strDescriptionEN
        }

    }

}