package com.example.luthfihariz.footballclub.ui.matchdetail

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.common.extension.loadImageUrl
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        viewModel.apply {
            matchId = intent.getStringExtra(ARG_MATCH_ID)
            matchDetailResource.observe(this@MatchDetailActivity, Observer { observeData(it) })
            getMatchDetail()
        }
    }

    private fun observeData(resource: Resource<Match>?) {
        resource?.let {
            when (resource.status) {

                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
                Status.SUCCESS -> {
                    bindData(it.data)
                }
            }
        }
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
        }
    }
}