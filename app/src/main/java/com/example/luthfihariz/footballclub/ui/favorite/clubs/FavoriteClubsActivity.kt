package com.example.luthfihariz.footballclub.ui.favorite.clubs

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.Status
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.ui.clubs.ClubsAdapter
import com.example.luthfihariz.footballclub.ui.clubsdetail.ClubDetailActivity
import com.example.luthfihariz.footballclub.ui.favorite.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite_matches.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class FavoriteClubsActivity : BaseActivity() {

    private val viewModel by viewModel<FavoriteViewModel>()
    private val adapter by inject<ClubsAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite_matches)
        setupRecyclerView()

    }

    override fun onResume() {
        super.onResume()
        with(viewModel) {
            favoriteClubsResource.observe(this@FavoriteClubsActivity, Observer { observeFavClubs(it) })
            getFavoriteClubs()
        }
    }

    private fun observeFavClubs(res: Resource<List<Club>>?) {
        res?.let {
            when (it.status) {
                Status.SUCCESS -> {
                    it.data?.let {
                        adapter.clubs = it
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
        rvFav.layoutManager = LinearLayoutManager(this)
        adapter.clickListener = {
            startActivity<ClubDetailActivity>("club" to it)
        }
        rvFav.adapter = adapter
    }
}