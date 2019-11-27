package com.shengshijie.bright.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.bright.IDialog
import com.shengshijie.bright.IRadioDialog

class RadioDialogImpl(context: Context, items: Array<String>?, defaultIndex: Int) : IRadioDialog {

    private var index: Int = defaultIndex

    private val radioDialog: AlertDialog = AlertDialog.Builder(context)
            .setSingleChoiceItems(
                    items, defaultIndex
            ) { _, which -> index = which }.create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setPositiveButton(text: String?, f: (IRadioDialog, index: Int) -> Unit) {
        radioDialog.setButton(BUTTON_POSITIVE, text) { _, _ ->
            f.invoke(this@RadioDialogImpl, index)
        }
    }

    override fun setNegativeButton(text: String?, f: (IRadioDialog, index: Int) -> Unit) {
        radioDialog.setButton(BUTTON_NEGATIVE, text) { _, _ ->
            f.invoke(this@RadioDialogImpl, index)
        }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        radioDialog.setOnDismissListener { f.invoke(this@RadioDialogImpl) }
    }

    override fun setMessage(message: String?) {
        radioDialog.setMessage(message)
    }

    override fun show() {
        radioDialog.show()
    }

    override fun dismiss() {
        radioDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        radioDialog.setTitle(title)
    }

}