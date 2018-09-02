package com.example.luthfihariz.footballclub.ui.matchdetail

import android.arch.lifecycle.Observer
import android.os.Bundle
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.Resource
import com.example.luthfihariz.footballclub.data.Status
import com.example.luthfihariz.footballclub.data.model.Match
import org.koin.android.architecture.ext.viewModel

class MatchDetailActivity : BaseActivity() {

    companion object {

        const val ARG_MATCH_ID = "matchId"
    }

    private val viewModel by viewModel<MatchDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_detail)

        viewModel.apply {
            matchId = intent.getStringExtra(ARG_MATCH_ID)
            matchDetailResource.observe(this@MatchDetailActivity, Observer { observeData(it) })
            getMatchDetail()
        }
    }

    private fun observeData(resource: Resource<Match>?) {
        resource?.let {
            when (resource.status) {

                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
                Status.SUCCESS -> {

                }
            }
        }
    }
}