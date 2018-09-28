package com.example.luthfihariz.footballclub.ui.favorite.match

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.Status
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.ui.favorite.FavoriteViewModel
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity
import com.example.luthfihariz.footballclub.ui.matches.MatchesAdapter
import kotlinx.android.synthetic.main.activity_favorite_matches.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class FavoriteMatchesActivity : BaseActivity() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private val adapter by inject<MatchesAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_matches)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        with(viewModel) {
            favoriteMatchesResource.observe(this@FavoriteMatchesActivity, Observer { observeFavMatches(it) })
            getFavoriteMatches()
        }
    }

    private fun observeFavMatches(res: Resource<List<Match>>?) {
        res?.let {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        adapter.matches = it
                    }
                }
                Status.ERROR -> {
                }
                Status.LOADING -> {
                }
            }
        }
    }

    private fun setupRecyclerView() {
        adapter.clickListener = {
            startActivity<MatchDetailActivity>(MatchDetailActivity.ARG_MATCH_ID to it.idEvent)
        }
        rvFav.layoutManager = LinearLayoutManager(this)
        rvFav.adapter = adapter
    }
}