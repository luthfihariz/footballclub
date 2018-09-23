package com.example.luthfihariz.footballclub.ui.home

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.base.BaseActivity
import com.example.luthfihariz.footballclub.ui.clubs.ClubsFragment
import com.example.luthfihariz.footballclub.ui.favorite.FavoriteFragment
import com.example.luthfihariz.footballclub.ui.matches.MatchesFragment
import com.example.luthfihariz.footballclub.ui.search.clubs.SearchClubsActivity
import com.example.luthfihariz.footballclub.ui.search.matches.SearchMatchesActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.jetbrains.anko.startActivity

class HomeActivity : BaseActivity() {

    private var searchType: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        bnvMainNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_match -> {
                    searchType = "match"
                    refreshMenu()
                    replaceContainer(MatchesFragment.newInstance(MatchesFragment.NEXT_MATCH))
                    true
                }
                R.id.action_team -> {
                    searchType = "team"
                    refreshMenu()
                    replaceContainer(ClubsFragment.newInstance())
                    true
                }

                R.id.action_fav -> {
                    searchType = null
                    refreshMenu()
                    replaceContainer(FavoriteFragment.newInstance())
                    true
                }

                else -> {
                    false
                }
            }
        }
        bnvMainNav.selectedItemId = R.id.action_match
        setSupportActionBar(toolbar)
    }

    private fun refreshMenu() {
        invalidateOptionsMenu()
    }

    private fun replaceContainer(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.flContainer, fragment, fragment.javaClass.simpleName)
                .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return searchType?.let {
            menuInflater.inflate(R.menu.home, menu)
            true
        } ?: false
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == R.id.action_search) {
            if (searchType == "match") {
                startActivity<SearchMatchesActivity>()
            } else if (searchType == "team") {
                startActivity<SearchClubsActivity>()
            }
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }
}