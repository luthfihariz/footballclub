package com.example.luthfihariz.footballclub.ui.matches.prevmatch

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.common.extension.gone
import com.example.luthfihariz.footballclub.common.extension.visible
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.Status
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity.Companion.ARG_MATCH_ID
import com.example.luthfihariz.footballclub.ui.matches.MatchesAdapter
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesViewModel
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class PrevMatchActivity : BaseActivity() {

    private val viewModel by viewModel<MatchesViewModel>()
    private val adapter by inject<MatchesAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prev_match)
        setupRecyclerView()

        viewModel.apply {
            matchesResource.observe(this@PrevMatchActivity, Observer { updateList(it) })
            getMatches(PREV_MATCH, intent.getStringExtra("leagueId"))
        }
    }

    private fun updateList(resource: Resource<List<Match>>?) {
        resource?.let {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    it.data?.let { matches ->
                        adapter.matches = matches
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                }
            }
        }
    }

    private fun showLoading() {
        pbMatches.visible()
        rvMatches.gone()
    }

    private fun hideLoading() {
        pbMatches.gone()
        rvMatches.visible()
    }

    private fun setupRecyclerView() {
        adapter.clickListener = {
            startActivity<MatchDetailActivity>(ARG_MATCH_ID to it.idEvent)
        }
        rvMatches.layoutManager = LinearLayoutManager(this)
        rvMatches.adapter = adapter
    }
}