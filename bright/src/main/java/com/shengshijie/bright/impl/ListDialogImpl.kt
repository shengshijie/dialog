package com.shengshijie.bright.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.bright.IDialog
import com.shengshijie.bright.IListDialog

class ListDialogImpl(
        context: Context,
        items: Array<String>?,
        f: (IListDialog, index: Int) -> Unit
) : IListDialog {

    private val radioDialog: AlertDialog = AlertDialog.Builder(context)
            .setItems(
                    items
            ) { _, which -> f.invoke(this@ListDialogImpl, which) }.create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        radioDialog.setOnDismissListener { f.invoke(this@ListDialogImpl) }
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