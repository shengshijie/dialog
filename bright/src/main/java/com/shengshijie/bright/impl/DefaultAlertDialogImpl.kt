package com.shengshijie.bright.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.bright.IAlertDialog
import com.shengshijie.bright.IDialog

class DefaultAlertDialogImpl(context: Context) : IAlertDialog {


    private val alertDialog: AlertDialog = AlertDialog.Builder(context).create().apply {
        window?.setGravity(Gravity.CENTER)
    }

    override fun setPositiveButton(text: String?, f: (IAlertDialog) -> Unit) {
        alertDialog.setButton(BUTTON_POSITIVE, text) { _, _ -> f.invoke(this@DefaultAlertDialogImpl) }
    }

    override fun setNegativeButton(text: String?, f: (IAlertDialog) -> Unit) {
        alertDialog.setButton(BUTTON_NEGATIVE, text) { _, _ -> f.invoke(this@DefaultAlertDialogImpl) }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        alertDialog.setOnDismissListener { f.invoke(this@DefaultAlertDialogImpl) }
    }

    override fun setMessage(message: String?) {
        alertDialog.setMessage(message)
    }

    override fun show() {
        alertDialog. show()
    }

    override fun dismiss() {
        alertDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        alertDialog.setTitle(title)
    }

}