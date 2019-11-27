package com.shengshijie.dialog.impl

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IListDialog

class DefaultListDialogImpl(
        context: Context,
        items: Array<String>?,
        f: (IListDialog, index: Int) -> Unit
) : IListDialog {

    private val radioDialog: AlertDialog = AlertDialog.Builder(context)
            .setItems(
                    items
            ) { _, which -> f.invoke(this@DefaultListDialogImpl, which) }.create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        radioDialog.setOnDismissListener { f.invoke(this@DefaultListDialogImpl) }
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