package com.example.luthfihariz.footballclub.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.ui.favorite.FavoriteFragment
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        bnvMainNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_match -> {
                    replaceContainer(MatchesFragment.newInstance(MatchesFragment.NEXT_MATCH))
                    true
                }
                R.id.action_team -> {

                    true
                }

                R.id.action_fav -> {
                    replaceContainer(FavoriteFragment.newInstance())
                    true
                }

                else -> {
                    false
                }
            }
        }
        bnvMainNav.selectedItemId = R.id.action_match
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, fragment, fragment.javaClass.simpleName)
                .commit()
    }
}