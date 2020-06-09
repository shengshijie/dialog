package com.shengshijie.dialog.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.ICheckDialog
import com.shengshijie.dialog.idialog.IDialog
import java.lang.reflect.Field

class DefaultCheckDialogImpl(context: Context, items: Array<String>?, checkedItems: BooleanArray) :
    ICheckDialog {

    private val checkDialog: AlertDialog = AlertDialog.Builder(context)
            .setMultiChoiceItems(items, checkedItems) { _, _, _ -> }
            .create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setPositiveButton(text: String?, f: (ICheckDialog) -> Unit) {
        checkDialog.setButton(BUTTON_POSITIVE, text) { _, _ ->
            f.invoke(this@DefaultCheckDialogImpl)
        }
    }

    override fun setNegativeButton(text: String?, f: (ICheckDialog) -> Unit) {
        checkDialog.setButton(BUTTON_NEGATIVE, text) { _, _ ->
            f.invoke(this@DefaultCheckDialogImpl)
        }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        checkDialog.setOnDismissListener { f.invoke(this@DefaultCheckDialogImpl) }
    }

    override fun setMessage(message: String?) {
        checkDialog.setMessage(message)
    }

    override fun setCancelable(cancelable: Boolean) {
        checkDialog.setCancelable(cancelable)
    }

    override fun show() {
        try {
            var field: Field? = checkDialog.javaClass.getDeclaredField("mAlert")
            field?.isAccessible = true
            val obj: Any? = field?.get(checkDialog)
            field = obj?.javaClass?.getDeclaredField("mHandler")
            field?.isAccessible = true
            field?.set(obj, ButtonHandler(checkDialog))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        checkDialog.show()
    }

    override fun dismiss() {
        checkDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        checkDialog.setTitle(title)
    }

}