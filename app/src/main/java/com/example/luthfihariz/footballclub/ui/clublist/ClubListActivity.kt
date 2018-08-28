package com.example.luthfihariz.footballclub.ui.clublist

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import org.jetbrains.anko.AnkoComponent
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.frameLayout
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.setContentView

class ClubListActivity : BaseActivity() {

    lateinit var clubsRecyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        frameLayout {
            clubsRecyclerView = recyclerView {
                layoutManager = LinearLayoutManager(context)
                adapter = ClubListAdapter()
            }
        }

        clubsRecyclerView.adapter


    }


}