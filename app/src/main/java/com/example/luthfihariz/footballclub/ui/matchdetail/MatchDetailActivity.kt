package com.example.luthfihariz.footballclub.ui.matchdetail

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.R.id.*
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.common.extension.gone
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
import com.example.luthfihariz.footballclub.common.extension.visible
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.Status
import com.example.luthfihariz.footballclub.data.model.Match
import com.luthfihariz.kocax.adjustTimePattern
import kotlinx.android.synthetic.main.activity_match_detail.*
import org.koin.android.architecture.ext.viewModel

class MatchDetailActivity : BaseActivity() {

    companion object {

        const val ARG_MATCH_ID = "matchId"
    }

    private val viewModel by viewModel<MatchDetailViewModel>()
    private

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        viewModel.apply {
            matchId = intent.getStringExtra(ARG_MATCH_ID)
            matchDetailResource.observe(this@MatchDetailActivity, Observer { observeData(it) })
            favoriteState.observe(this@MatchDetailActivity, Observer { observeFavoriteState(it) })
            getMatchDetail()
        }

        setupToolbar()
    }

    private fun observeFavoriteState(favorite: Boolean?) {
        when (favorite) {
            true -> {

            }

            false -> {

            }
        }
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        return super.onPrepareOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when {
            item?.itemId == android.R.id.home -> {
                finish()
                true
            }
            item?.itemId == R.id.add_to_favorite -> {
                viewModel.addToFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun observeData(resource: Resource<Match>?) {
        resource?.let {
            when (resource.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.ERROR -> {
                    hideLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    bindData(it.data)
                }
            }
        }
    }

    private fun showLoading() {
        pbMatchDetail.visible()
        svContent.gone()
    }

    private fun hideLoading() {
        pbMatchDetail.gone()
        svContent.visible()
    }

    private fun bindData(data: Match?) {
        data?.let {
            tvDate.text = it.strDate.adjustTimePattern("dd/MM/yy", "EEE, dd MMM yyyy")
            tvAwayTeam.text = it.strAwayTeam
            tvHomeTeam.text = it.strHomeTeam
            ivAwayLogo.loadImageUrl(it.awayTeam?.strTeamBadge)
            ivHomeLogo.loadImageUrl(it.homeTeam?.strTeamBadge)

            tvScoreHome.text = it.intHomeScore?.toString()
            tvScoreAway.text = it.intAwayScore?.toString()
            tvAwayForm.text = it.strAwayFormation
            tvHomeForm.text = it.strHomeFormation
            tvGoalsHome.text = it.strHomeGoalDetails
            tvGoalsAway.text = it.strAwayGoalDetails
            tvShotsAway.text = it.intAwayShots?.toString()
            tvShotsHome.text = it.intHomeShots?.toString()

            tvGoalKeeperAway.text = it.strAwayLineupGoalkeeper
            tvGoalKeeperHome.text = it.strHomeLineupGoalkeeper

            tvMidfieldAway.text = it.strAwayLineupMidfield
            tvMidfieldHome.text = it.strHomeLineupMidfield
            tvForwardAway.text = it.strAwayLineupForward
            tvForwardHome.text = it.strHomeLineupForward

            tvSubstituteAway.text = it.strAwayLineupSubstitutes
            tvSubstituteHome.text = it.strHomeLineupSubstitutes
        }
    }
}