package com.example.luthfihariz.footballclub.ui.matches

import android.arch.lifecycle.Observer
import android.content.Intent
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
import com.example.luthfihariz.footballclub.data.model.League
import com.example.luthfihariz.footballclub.data.model.Match
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity
import com.example.luthfihariz.footballclub.ui.matchdetail.MatchDetailActivity.Companion.ARG_MATCH_ID
import com.example.luthfihariz.footballclub.ui.matches.leaguepicker.LeaguePickerDialog
import com.example.luthfihariz.footballclub.ui.matches.leaguepicker.LeaguePickerDialog.Companion.REQ_CODE
import com.example.luthfihariz.footballclub.ui.matches.prevmatch.PrevMatchActivity
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
            nextMatchResource.observe(this@MatchesFragment, Observer { updateList(it) })
            selectedLeague.observe(this@MatchesFragment, Observer { setupLeaguePicker(it!!) })
            getMatchByLeague()
        }
    }

    private fun setupLeaguePicker(selectedLeague: League) {
        tvLeague.setOnClickListener {
            showLeaguePicker(selectedLeague)
        }
        ivLeaguePicker.setOnClickListener {
            showLeaguePicker(selectedLeague)
        }
        tvLeague.text = selectedLeague.name
        
        btnPrev.setOnClickListener {
            startActivity<PrevMatchActivity>("leagueId" to selectedLeague.id)
        }
    }

    private fun showLeaguePicker(selectedLeague: League) {
        LeaguePickerDialog.newInstance(viewModel.leagues, selectedLeague.id).apply {
            setTargetFragment(this@MatchesFragment, REQ_CODE)
        }.show(fragmentManager, LeaguePickerDialog::class.java.simpleName)
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
        btnPrev.gone()
    }

    private fun hideLoading() {
        pbMatches.gone()
        rvMatches.visible()
        btnPrev.visible()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            REQ_CODE -> {
                val selectedLeague = data?.getParcelableExtra<League>("league")
                selectedLeague?.let {
                    viewModel.selectedLeague.value = it
                    viewModel.getMatches(NEXT_MATCH, it.id)
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }

    }

    private fun setupRecyclerView() {
        adapter.clickListener = {
            startActivity<MatchDetailActivity>(ARG_MATCH_ID to it.idEvent)
        }
        rvMatches.layoutManager = LinearLayoutManager(context)
        rvMatches.adapter = adapter
    }
}