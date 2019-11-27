package com.shengshijie.bright.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.shengshijie.bright.IDialog
import com.shengshijie.bright.IInputDialog

class DefaultInputDialogImpl(context: Context) : IInputDialog {

    private val editText = EditText(context)
    private val alertDialog: AlertDialog = AlertDialog.Builder(context).setView(editText).create().apply {
        window?.setGravity(Gravity.CENTER)
    }

    override fun setPositiveButton(text: String?, f: (IInputDialog, text: String?) -> Unit) {
        alertDialog.setButton(BUTTON_POSITIVE, text) { _, _ -> f.invoke(this@DefaultInputDialogImpl, editText.text.toString()) }
    }

    override fun setNegativeButton(text: String?, f: (IInputDialog, text: String?) -> Unit) {
        alertDialog.setButton(BUTTON_NEGATIVE, text) { _, _ -> f.invoke(this@DefaultInputDialogImpl, editText.text.toString()) }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        alertDialog.setOnDismissListener { f.invoke(this@DefaultInputDialogImpl) }
    }

    override fun setMessage(message: String?) {
        alertDialog.setMessage(message)
    }

    override fun show() {
        alertDialog.show()
    }

    override fun dismiss() {
        alertDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        alertDialog.setTitle(title)
    }

}