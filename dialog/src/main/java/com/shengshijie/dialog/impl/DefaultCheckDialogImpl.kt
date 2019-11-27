package com.shengshijie.dialog.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.ICheckDialog
import com.shengshijie.dialog.idialog.IDialog

class DefaultCheckDialogImpl(context: Context, items: Array<String>?, checkedItems: BooleanArray) :
    ICheckDialog {

    private val radioDialog: AlertDialog = AlertDialog.Builder(context)
            .setMultiChoiceItems(items, checkedItems) { _, _, _ -> }
            .create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setPositiveButton(text: String?, f: (ICheckDialog) -> Unit) {
        radioDialog.setButton(BUTTON_POSITIVE, text) { _, _ ->
            f.invoke(this@DefaultCheckDialogImpl)
        }
    }

    override fun setNegativeButton(text: String?, f: (ICheckDialog) -> Unit) {
        radioDialog.setButton(BUTTON_NEGATIVE, text) { _, _ ->
            f.invoke(this@DefaultCheckDialogImpl)
        }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        radioDialog.setOnDismissListener { f.invoke(this@DefaultCheckDialogImpl) }
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