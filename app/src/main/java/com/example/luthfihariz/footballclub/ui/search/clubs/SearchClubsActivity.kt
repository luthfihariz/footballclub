package com.example.luthfihariz.footballclub.ui.search.clubs

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.ui.clubs.ClubsAdapter
import com.example.luthfihariz.footballclub.ui.clubsdetail.ClubDetailActivity
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class SearchClubsActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<SearchClubsViewModel>()
    private val adapter by inject<ClubsAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupRecyclerView()
        setupSearchView()
        viewModel.clubs.observe(this@SearchClubsActivity, Observer { updateSearchResult(it) })
    }

    private fun updateSearchResult(data: List<Club>?) {
        adapter.clubs = data ?: arrayListOf()
    }

    private fun setupSearchView() {
        svSearch.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView() {
        adapter.clickListener = {
            startActivity<ClubDetailActivity>("club" to it)
        }

        rvSearchResult.layoutManager = LinearLayoutManager(this)
        rvSearchResult.adapter = adapter
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let {
            if (it.length > 2) {
                viewModel.searchClubs(p0)
                return true
            }
        }

        return false
    }
}