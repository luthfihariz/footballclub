package com.example.luthfihariz.footballclub.ui.matches.favorite

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.extension.gone
import com.example.luthfihariz.footballclub.common.extension.visible
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.Status
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity
import com.example.luthfihariz.footballclub.ui.matches.MatchesAdapter
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class FavoriteFragment : Fragment() {

    companion object {

        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }

    private val viewModel by viewModel<FavoriteViewModel>()
    private val adapter by inject<MatchesAdapter>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onResume() {
        super.onResume()
        viewModel.apply {
            favoriteMatchesResource.observe(this@FavoriteFragment, Observer { updateList(it) })
            getFavoriteMatches()
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
            startActivity<MatchDetailActivity>(MatchDetailActivity.ARG_MATCH_ID to it.idEvent)
        }
        rvMatches.layoutManager = LinearLayoutManager(context)
        rvMatches.adapter = adapter
    }

}