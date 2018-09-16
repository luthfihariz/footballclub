package com.example.luthfihariz.footballclub.common.widget

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.support.v4.app.DialogFragment
import android.view.WindowManager
import com.example.luthfihariz.footballclub.R

/**
* Created by luthfihariz on 03/05/18.
*
* Dialog fragment with Popup Animation (slide in and out when enter and exit)
*/
open class PopupDialog : DialogFragment() {

    override fun onStart() {
        super.onStart()
        dialog.window.apply {
            attributes.windowAnimations = R.style.PopupAnimation
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }
}