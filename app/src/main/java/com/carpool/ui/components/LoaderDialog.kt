package com.carpool.ui.components

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.carpool.R

/**
 * LoaderDialogFragment.java to implement loader view to block user interactions during the API call or any critical action.
 *
 * @author Sino K D
 * @since 16/08/17
 */

class LoaderDialog : DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val v = inflater.inflate(R.layout.loader_dialog_layout, container, false)

        dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        return v
    }
}