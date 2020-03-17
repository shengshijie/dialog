package com.shengshijie.dialog.impl

import android.content.Context
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IListDialog
import java.lang.reflect.Field

class DefaultListDialogImpl(
        context: Context,
        items: Array<String>?,
        f: (IListDialog, index: Int) -> Unit
) : IListDialog {

    private val listDialog: AlertDialog = AlertDialog.Builder(context)
            .setItems(
                    items
            ) { _, which -> f.invoke(this@DefaultListDialogImpl, which) }.create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        listDialog.setOnDismissListener { f.invoke(this@DefaultListDialogImpl) }
    }

    override fun setMessage(message: String?) {
        listDialog.setMessage(message)
    }

    override fun setCancelable(cancelable: Boolean) {
        listDialog.setCancelable(cancelable)
    }

    override fun show() {
        try {
            var field: Field? = listDialog.javaClass.getDeclaredField("mAlert")
            field?.isAccessible = true
            val obj: Any? = field?.get(listDialog)
            field = obj?.javaClass?.getDeclaredField("mHandler")
            field?.isAccessible = true
            field?.set(obj, ButtonHandler(listDialog))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        listDialog.show()
    }

    override fun dismiss() {
        listDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        listDialog.setTitle(title)
    }

}