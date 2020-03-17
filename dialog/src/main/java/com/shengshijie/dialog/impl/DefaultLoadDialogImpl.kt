package com.shengshijie.dialog.impl

import android.app.ProgressDialog
import android.content.Context
import android.view.Gravity
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.ILoadDialog
import java.lang.reflect.Field

class DefaultLoadDialogImpl(context: Context) :
    ILoadDialog {

    private val loadDialog = ProgressDialog(context).apply {
        window?.setGravity(Gravity.CENTER)
    }

    override fun setMessage(message: String?) {
        loadDialog.setMessage(message)
    }

    override fun show() {
        try {
            var field: Field? = loadDialog.javaClass.getDeclaredField("mAlert")
            field?.isAccessible = true
            val obj: Any? = field?.get(loadDialog)
            field = obj?.javaClass?.getDeclaredField("mHandler")
            field?.isAccessible = true
            field?.set(obj, ButtonHandler(loadDialog))
        } catch (e: Exception) {
            e.printStackTrace()
        }
        loadDialog.show()
    }

    override fun dismiss() {
        loadDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        loadDialog.setTitle(title)
    }

    override fun setIndeterminate(indeterminate: Boolean) {
        if (indeterminate) loadDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER) else loadDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL)
    }

    override fun setCancelable(cancelable: Boolean) {
        loadDialog.setCancelable(cancelable)
    }

    override fun setProgress(progress: Int) {
        loadDialog.progress = progress
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        loadDialog.setOnDismissListener { f.invoke(this@DefaultLoadDialogImpl) }
    }

}