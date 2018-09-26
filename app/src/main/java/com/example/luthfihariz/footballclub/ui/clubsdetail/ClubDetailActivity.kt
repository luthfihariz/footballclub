package com.example.luthfihariz.footballclub.ui.clubsdetail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.common.extension.gone
import com.example.luthfihariz.footballclub.common.extension.invisible
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.common.extension.visible
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.Player
import com.example.luthfihariz.footballclub.ui.player.PlayerDetailActivity
import kotlinx.android.synthetic.main.activity_club_detail.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class ClubDetailActivity : BaseActivity() {

    private val viewModel by viewModel<ClubDetailViewModel>()
    private val adapter by inject<ClubPlayersAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_club_detail)

        val club = intent.getParcelableExtra<Club>("club")
        setupPlayerRecyclerView()
        bindData(club)

        with(viewModel) {
            players.observe(this@ClubDetailActivity, Observer { updatePlayerList(it) })
            favoriteState.observe(this@ClubDetailActivity, Observer { observeFavoriteState(it) })
            snackBarEvent.observe(this@ClubDetailActivity, Observer { observeSnackBar(it) })
            this.club = club
            checkFavoriteState()
            showLoading()
            getPlayers()
        }

        ivFavorite.setOnClickListener {
            viewModel.toggleFavorite()
        }
    }

    private fun observeSnackBar(message: String?) {
        message?.let {
            snackbar(clRoot, it)
        }
    }

    private fun observeFavoriteState(isFavorite: Boolean?) {
        when (isFavorite) {
            true -> ivFavorite.setImageResource(R.drawable.ic_added_to_favorites)
            false -> ivFavorite.setImageResource(R.drawable.ic_add_to_favorites)
        }
    }

    private fun updatePlayerList(players: List<Player>?) {
        players?.let {
            hideLoading()
            adapter.players = it
        }
    }

    private fun showLoading() {
        pbPlayers.visible()
        rvPlayer.invisible()
        tvOverview.gone()
    }

    private fun hideLoading() {
        pbPlayers.gone()
        rvPlayer.visible()
        tvOverview.visible()
    }

    private fun setupPlayerRecyclerView() {
        rvPlayer.layoutManager = LinearLayoutManager(this)
        rvPlayer.adapter = adapter.apply {
            clickListener = {
                startActivity<PlayerDetailActivity>("player" to it)
            }
        }
    }

    private fun bindData(club: Club?) {
        club?.let {
            ivLogo.loadImageUrl(club.strTeamBadge)
            ivClubStadiumThumb.loadImageUrl(club.strStadiumThumb)
            tvName.text = club.strTeam
            tvFormed.text = club.intFormedYear
            tvOverview.text = club.strDescriptionEN
            tvStadiumName.text = club.strStadium
        }
    }

}