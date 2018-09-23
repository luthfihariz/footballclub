package com.example.luthfihariz.footballclub.ui.favorite

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.ui.favorite.clubs.FavoriteClubsActivity
import com.example.luthfihariz.footballclub.ui.favorite.match.FavoriteMatchesActivity
import kotlinx.android.synthetic.main.fragment_favorites.*
import org.jetbrains.anko.support.v4.startActivity

class FavoriteFragment : Fragment() {

    companion object {
        fun newInstance(): FavoriteFragment = FavoriteFragment()
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cvMatches.setOnClickListener {
            startActivity<FavoriteMatchesActivity>()
        }

        cvClubs.setOnClickListener {
            startActivity<FavoriteClubsActivity>()
        }
    }


}