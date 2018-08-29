package com.example.luthfihariz.footballclub.ui.clublist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.data.FootballClubRepository
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView

class ClubListActivity : BaseActivity() {

    lateinit var clubsRecyclerView: RecyclerView

    // should use injection here
    private val clubListAdapter: ClubListAdapter by lazy {
        ClubListAdapter()
    }
    private val repository: FootballClubRepository by lazy {
        FootballClubRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        frameLayout {
            clubsRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = clubListAdapter
            }
        }

        clubListAdapter.footballClubs = repository.getClubs()
    }


}