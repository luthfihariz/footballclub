package com.example.luthfihariz.footballclub.ui.search.matches

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity
import com.example.luthfihariz.footballclub.ui.matches.MatchesAdapter
import kotlinx.android.synthetic.main.activity_search.*
import org.jetbrains.anko.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class SearchMatchesActivity : BaseActivity(), SearchView.OnQueryTextListener {

    private val viewModel by viewModel<SearchMatchesViewModel>()
    private val adapter by inject<MatchesAdapter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setupRecyclerView()
        setupSearchView()
        viewModel.matches.observe(this@SearchMatchesActivity, Observer { updateSearchResult(it) })
    }

    private fun setupSearchView() {
        svSearch.setOnQueryTextListener(this)
    }

    private fun setupRecyclerView() {
        adapter.clickListener = {
            startActivity<MatchDetailActivity>(MatchDetailActivity.ARG_MATCH_ID to it.idEvent)
        }

        rvSearchResult.layoutManager = LinearLayoutManager(this)
        rvSearchResult.adapter = adapter
    }

    private fun updateSearchResult(matches: List<Match>?) {
        adapter.matches = matches ?: arrayListOf()
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let {
            if (it.length > 2) {
                viewModel.searchMatches(p0)
                return true
            }
        }

        return false
    }


}