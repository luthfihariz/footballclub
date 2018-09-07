package com.example.luthfihariz.footballclub.ui.matches

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.NEXT_MATCH
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment.Companion.PREV_MATCH
import com.example.luthfihariz.footballclub.ui.matches.favorite.FavoriteFragment
import kotlinx.android.synthetic.main.activity_matches.*

class MatchesActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_matches)

        bnvMainNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_prev_match -> {
                    replaceContainer(MatchesFragment.newInstance(PREV_MATCH))
                    true
                }
                R.id.action_next_match -> {
                    replaceContainer(MatchesFragment.newInstance(NEXT_MATCH))
                    true
                }

                R.id.action_fav_match -> {
                    replaceContainer(FavoriteFragment.newInstance())
                    true
                }

                else -> {
                    false
                }
            }
        }
        bnvMainNav.selectedItemId = R.id.action_prev_match
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, fragment)
                .commit()
    }
}