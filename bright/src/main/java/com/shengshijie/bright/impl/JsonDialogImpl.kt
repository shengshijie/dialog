package com.shengshijie.bright.impl

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.DialogInterface.BUTTON_POSITIVE
import android.text.method.ScrollingMovementMethod
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.dandan.jsonhandleview.library.JsonViewLayout
import com.shengshijie.bright.IDialog
import com.shengshijie.bright.IJsonDialog

class JsonDialogImpl(context: Context) : IJsonDialog {

    private var jsonViewLayout: JsonViewLayout? = null
    private var json: String? = ""

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
        addView(JsonViewLayout(context).apply {
            jsonViewLayout = this
        })
    }

    private val alertDialog: AlertDialog =
            AlertDialog.Builder(context).setView(linearLayout).create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setOnCopy(f: (IJsonDialog) -> Unit) {
        alertDialog.setButton(BUTTON_POSITIVE, "复制") { _, _ ->
            val cm =
                    alertDialog.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val mClipData = ClipData.newPlainText("copy", json)
            cm.setPrimaryClip(mClipData)
            f.invoke(this@JsonDialogImpl)
        }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        alertDialog.setOnDismissListener { f.invoke(this@JsonDialogImpl) }
    }

    override fun setMessage(message: String?) {
        json = message
        try {
            jsonViewLayout?.bindJson(message)
        } catch (e: Exception) {
            jsonViewLayout?.bindJson("{'message': $message}")
        }
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