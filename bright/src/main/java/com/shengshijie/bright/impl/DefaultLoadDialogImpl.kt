package com.shengshijie.bright.impl

import android.app.ProgressDialog
import android.content.Context
import android.view.Gravity
import com.shengshijie.bright.IDialog
import com.shengshijie.bright.ILoadDialog

class DefaultLoadDialogImpl(context: Context) : ILoadDialog {

    private val loadDialog = ProgressDialog(context).apply {
        window?.setGravity(Gravity.CENTER)
    }

    override fun setMessage(message: String?) {
        loadDialog.setMessage(message)
    }

    override fun show() {
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

    override fun setNegativeable(Negativeable: Boolean) {
        loadDialog.setCancelable(Negativeable)
    }

    override fun setProgress(progress: Int) {
        loadDialog.progress = progress
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        loadDialog.setOnDismissListener { f.invoke(this@DefaultLoadDialogImpl) }
    }

}