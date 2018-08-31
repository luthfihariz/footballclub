package com.example.luthfihariz.footballclub.ui.matches

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.data.model.Match
import kotlinx.android.synthetic.main.fragment_matches.*
import org.jetbrains.anko.bundleOf
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
            getMatches(arguments?.getInt(ARG_SCHEDULE_TYPE) ?: 0)
        }

    }

    private fun updateList(matches: List<Match>?) {
        matches?.let {
            adapter.matches = matches
        }
    }

    private fun setupRecyclerView() {
        rvMatches.layoutManager = LinearLayoutManager(context)
        rvMatches.adapter = adapter
    }
}