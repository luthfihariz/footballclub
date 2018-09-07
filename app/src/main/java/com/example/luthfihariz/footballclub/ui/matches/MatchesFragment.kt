package com.example.luthfihariz.footballclub.ui.matches

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
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity.Companion.ARG_MATCH_ID
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.bundleOf
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class MatchesFragment : Fragment() {

    companion object {

        const val PREV_MATCH = 0
        const val NEXT_MATCH = 1

        private const val ARG_SCHEDULE_TYPE = "scheduleType"

        fun newInstance(type: Int): MatchesFragment = MatchesFragment().apply {
            arguments = bundleOf(ARG_SCHEDULE_TYPE to type)
        }
    }

    private val viewModel by viewModel<MatchesViewModel>()
    private val adapter by inject<MatchesAdapter>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_matches, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        viewModel.apply {
            matchesResource.observe(this@MatchesFragment, Observer { updateList(it) })
            nextMatchResource.observe(this@MatchesFragment, Observer { updateList(it) })
            getMatches(arguments?.getInt(ARG_SCHEDULE_TYPE) ?: 0)
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
        rvMatches.layoutManager = LinearLayoutManager(context)
        rvMatches.adapter = adapter
    }
}