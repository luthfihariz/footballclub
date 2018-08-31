package com.example.luthfihariz.footballclub.ui.clublist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.repository.FootballClubRepository
import com.example.luthfihariz.footballclub.ui.clubdetail.ClubDetailActivity
import com.example.luthfihariz.footballclub.ui.clubdetail.ClubDetailActivity.Companion.ARG_FOOTBALL_CLUB
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.startActivity

class ClubListActivity : BaseActivity() {

    private lateinit var clubsRecyclerView: RecyclerView

    // should use injection here
    private val clubListAdapter: ClubListAdapter by lazy {
        ClubListAdapter()
    }
    private val repository: FootballClubRepository by lazy {
        FootballClubRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        constructView()
        clubListAdapter.footballClubs = repository.getClubs()
        clubListAdapter.clickListener = {
            startActivity<ClubDetailActivity>(ARG_FOOTBALL_CLUB to it)
        }
    }


    private fun constructView() {
        frameLayout {
            clubsRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = clubListAdapter
            }
        }
    }
}