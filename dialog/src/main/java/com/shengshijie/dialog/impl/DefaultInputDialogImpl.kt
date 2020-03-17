package com.shengshijie.dialog.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IInputDialog
import java.lang.reflect.Field

class DefaultInputDialogImpl(context: Context) :
    IInputDialog {

    private val editText = EditText(context)
    private val inputDialog: AlertDialog =
        AlertDialog.Builder(context).setView(editText).create().apply {
            window?.setGravity(Gravity.CENTER)
        }

    override fun setPositiveButton(text: String?, f: (IInputDialog, text: String?) -> Unit) {
        inputDialog.setButton(BUTTON_POSITIVE, text) { _, _ -> f.invoke(this@DefaultInputDialogImpl, editText.text.toString()) }
    }

    override fun setNegativeButton(text: String?, f: (IInputDialog, text: String?) -> Unit) {
        inputDialog.setButton(BUTTON_NEGATIVE, text) { _, _ -> f.invoke(this@DefaultInputDialogImpl, editText.text.toString()) }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        inputDialog.setOnDismissListener { f.invoke(this@DefaultInputDialogImpl) }
    }

    override fun setMessage(message: String?) {
        inputDialog.setMessage(message)
    }

    override fun setCancelable(cancelable: Boolean) {
        inputDialog.setCancelable(cancelable)
    }

    override fun show() {
        try {
            var field: Field? = inputDialog.javaClass.getDeclaredField("mAlert")
            field?.isAccessible = true
            val obj: Any? = field?.get(inputDialog)
            field = obj?.javaClass?.getDeclaredField("mHandler")
            field?.isAccessible = true
            field?.set(obj, ButtonHandler(inputDialog))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        inputDialog.show()
    }

    override fun dismiss() {
        inputDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        inputDialog.setTitle(title)
    }

}