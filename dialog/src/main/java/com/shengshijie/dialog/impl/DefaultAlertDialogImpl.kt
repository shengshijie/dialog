package com.shengshijie.dialog.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.IAlertDialog
import com.shengshijie.dialog.idialog.IDialog
import java.lang.reflect.Field

class DefaultAlertDialogImpl(context: Context) :
    IAlertDialog {


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

    override fun setCancelable(cancelable: Boolean) {
        alertDialog.setCancelable(cancelable)
    }

    override fun show() {
        try {
            var field: Field? = alertDialog.javaClass.getDeclaredField("mAlert")
            field?.isAccessible = true
            val obj: Any? = field?.get(alertDialog)
            field = obj?.javaClass?.getDeclaredField("mHandler")
            field?.isAccessible = true
            field?.set(obj, ButtonHandler(alertDialog))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        alertDialog. show()
    }

    override fun dismiss() {
        alertDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        alertDialog.setTitle(title)
    }

}