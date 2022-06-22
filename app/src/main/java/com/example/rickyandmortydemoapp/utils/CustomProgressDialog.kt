package com.example.rickyandmortydemoapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import com.example.rickyandmortydemoapp.R

object CustomProgressDialog {

    fun showProgressDialog(ctx: Context?): Dialog {
        val dialog = Dialog(ctx!!)
        dialog.setContentView(R.layout.custom_popup_progress)
        dialog.setCancelable(false)
        if (dialog.window != null) {
            dialog.window!!
                .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
        val layoutParams = dialog.window!!.attributes
        layoutParams.dimAmount = 0.4.toFloat()
        return dialog
    }
}