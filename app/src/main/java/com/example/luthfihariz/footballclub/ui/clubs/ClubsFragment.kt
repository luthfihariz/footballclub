package com.example.luthfihariz.footballclub.ui.clubs

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
import com.example.luthfihariz.footballclub.data.model.Club
import com.example.luthfihariz.footballclub.data.model.League
import com.example.luthfihariz.footballclub.ui.clubsdetail.ClubDetailActivity
import com.example.luthfihariz.footballclub.ui.matches.leaguepicker.LeaguePickerDialog
import kotlinx.android.synthetic.main.fragment_clubs.*
import org.jetbrains.anko.support.v4.startActivity
import org.koin.android.architecture.ext.viewModel
import org.koin.android.ext.android.inject

class ClubsFragment : Fragment() {

    private val viewModel by viewModel<ClubsViewModel>()
    private val adapter by inject<ClubsAdapter>()

    companion object {

        fun newInstance(): ClubsFragment {
            return ClubsFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_clubs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()

        viewModel.apply {
            selectedLeague.observe(this@ClubsFragment, Observer { setupLeaguePicker(it!!) })
            clubsResource.observe(this@ClubsFragment, Observer { updateClubs(it!!) })
            getClubsByLeague()
        }
    }

    private fun updateClubs(resource: Resource<List<Club>>?) {
        resource?.let {
            when (it.status) {
                Status.LOADING -> {
                    showLoading()
                }
                Status.SUCCESS -> {
                    hideLoading()
                    it.data?.let { clubs ->
                        adapter.clubs = clubs
                    }
                }
                Status.ERROR -> {
                    hideLoading()
                }
            }
        }
    }

    private fun showLoading() {
        pbClubs.visible()
        rvClubs.gone()
    }

    private fun hideLoading() {
        pbClubs.gone()
        rvClubs.visible()
    }

    private fun setupLeaguePicker(selectedLeague: League) {
        tvClubLeague.setOnClickListener {
            showLeaguePicker(selectedLeague)
        }
        ivClubLeaguePicker.setOnClickListener {
            showLeaguePicker(selectedLeague)
        }
        tvClubLeague.text = selectedLeague.name
    }

    private fun showLeaguePicker(selectedLeague: League) {
        LeaguePickerDialog.newInstance(viewModel.leagues, selectedLeague.id).apply {
            setTargetFragment(this@ClubsFragment, LeaguePickerDialog.REQ_CODE)
        }.show(fragmentManager, LeaguePickerDialog::class.java.simpleName)
    }

    private fun setupRecyclerView() {
        adapter.clickListener = {
            startActivity<ClubDetailActivity>("club" to it)
        }
        rvClubs.layoutManager = LinearLayoutManager(context)
        rvClubs.adapter = adapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            LeaguePickerDialog.REQ_CODE -> {
                val selectedLeague = data?.getParcelableExtra<League>("league")
                selectedLeague?.let {
                    viewModel.selectedLeague.value = it
                    viewModel.getClubs()
                }
            }
            else -> {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }

    }
}
