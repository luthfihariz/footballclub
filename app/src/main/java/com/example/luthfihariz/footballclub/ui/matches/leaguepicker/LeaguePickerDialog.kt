package com.example.luthfihariz.footballclub.ui.matches.leaguepicker

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.luthfihariz.footballclub.R
import com.example.luthfihariz.footballclub.common.widget.PopupDialog
import com.example.luthfihariz.footballclub.data.model.League
import kotlinx.android.synthetic.main.dialog_league_picker.*
import org.jetbrains.anko.bundleOf

class LeaguePickerDialog : PopupDialog() {

    companion object {

        const val REQ_CODE = 133

        fun newInstance(leagues: List<League>, selectedLeagueId: String): LeaguePickerDialog {
            return LeaguePickerDialog().apply {
                arguments = bundleOf("leagues" to leagues,
                        "selectedLeague" to selectedLeagueId)
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_league_picker, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        arguments?.let { arg ->
            rvLeagues.layoutManager = LinearLayoutManager(context)
            rvLeagues.adapter = LeaguePickerAdapter().apply {
                leagues = arg.getParcelableArrayList("leagues")!!
                selectedId = arg.getString("selectedLeague")!!
                clickListener = { league ->
                    val data = Intent().apply {
                        putExtra("league", league)
                    }
                    targetFragment?.onActivityResult(REQ_CODE, RESULT_OK, data)
                    dismiss()
                }
            }
        }
    }

}