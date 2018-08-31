package com.example.luthfihariz.footballclub.ui.matches

import android.os.Bundle
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.NEXT_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)


        bnvMainNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_prev_match -> {
                    replaceContainer(PREV_MATCH)
                    true
                }
                R.id.action_next_match -> {
                    replaceContainer(NEXT_MATCH)
                    true
                }

                else -> {
                    false
                }
            }
        }
    }

    private fun replaceContainer(type: Int) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, MatchesFragment.newInstance(type))
                .commit()
    }
}