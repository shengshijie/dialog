package com.shengshijie.dialog.impl

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.IDebugDialog
import com.shengshijie.dialog.idialog.IDialog

class DefaultDebugDialogImpl(context: Context) :
    IDebugDialog {

    private var textView: TextView? = null

    private val linearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        setPadding(20, 20, 20, 20)
        addView(TextView(context).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(20, 20, 20, 20)
            isVerticalScrollBarEnabled = true
            movementMethod = ScrollingMovementMethod.getInstance()
            text = Thread.currentThread().name
            textSize = 20F
        })
        addView(TextView(context).apply {
            textView = this
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
            setPadding(20, 20, 20, 20)
            isVerticalScrollBarEnabled = true
            movementMethod = ScrollingMovementMethod.getInstance()
            textSize = 14F
        })
    }

    private val alertDialog: AlertDialog =
            AlertDialog.Builder(context).setView(linearLayout).create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setPositiveButton(text: String?, f: (IDebugDialog) -> Unit) {
        alertDialog.setButton(BUTTON_POSITIVE, text) { _, _ -> f.invoke(this@DefaultDebugDialogImpl) }
    }

    override fun setNegativeButton(text: String?, f: (IDebugDialog) -> Unit) {
        alertDialog.setButton(BUTTON_NEGATIVE, text) { _, _ -> f.invoke(this@DefaultDebugDialogImpl) }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        alertDialog.setOnDismissListener { f.invoke(this@DefaultDebugDialogImpl) }
    }

    override fun setMessage(message: String?) {
        textView?.text = message
    }

    override fun setOnCopyMessage(f: (IDebugDialog) -> Unit) {
        textView?.setOnLongClickListener {
            val cm =
                    alertDialog.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("copy", textView?.text)
            cm.setPrimaryClip(mClipData)
            f.invoke(this@DefaultDebugDialogImpl)
            true
        }
    }

    override fun setCancelable(cancelable: Boolean) {
        alertDialog.setCancelable(cancelable)
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