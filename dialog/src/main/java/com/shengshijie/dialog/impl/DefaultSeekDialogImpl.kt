package com.shengshijie.dialog.impl

import android.content.Context
import android.content.DialogInterface.BUTTON_NEGATIVE
import android.content.DialogInterface.BUTTON_POSITIVE
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.shengshijie.dialog.idialog.ISeekDialog
import com.shengshijie.dialog.idialog.IDialog

class DefaultSeekDialogImpl(context: Context, var defaultProgress: Int) :
    ISeekDialog {

    private var seekBar: SeekBar? = null
    private var textView: TextView? = null

    private val linearLayout = LinearLayout(context).apply {
        orientation = LinearLayout.VERTICAL
        layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        )
        setPadding(20, 20, 20, 20)
        addView(TextView(context).apply {
            gravity = Gravity.CENTER_HORIZONTAL
            textView = this
            text = defaultProgress.toString()
        })
        addView(
                SeekBar(context).apply {
                    seekBar = this
                    progress = defaultProgress
                    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                        override fun onProgressChanged(
                                seekBar: SeekBar?,
                                progress: Int,
                                fromUser: Boolean
                        ) {
                            defaultProgress = progress
                            textView?.text = defaultProgress.toString()
                        }

                        override fun onStartTrackingTouch(seekBar: SeekBar?) {

                        }

                        override fun onStopTrackingTouch(seekBar: SeekBar?) {

                        }
                    })
                }, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        ).apply {
            topMargin = 10
        }
        )
    }

    private val seekDialog: AlertDialog =
            AlertDialog.Builder(context).setView(linearLayout).create().apply {
                window?.setGravity(Gravity.CENTER)
            }

    override fun setProgress(progress: Int) {
        seekBar?.progress = progress
    }

    override fun setPositiveButton(text: String?, f: (ISeekDialog, index: Int) -> Unit) {
        seekDialog.setButton(BUTTON_POSITIVE, text) { _, _ ->
            f.invoke(this@DefaultSeekDialogImpl, defaultProgress)
        }
    }

    override fun setNegativeButton(text: String?, f: (ISeekDialog, index: Int) -> Unit) {
        seekDialog.setButton(BUTTON_NEGATIVE, text) { _, _ ->
            f.invoke(this@DefaultSeekDialogImpl, defaultProgress)
        }
    }

    override fun setOnDismiss(f: (IDialog) -> Unit) {
        seekDialog.setOnDismissListener { f.invoke(this@DefaultSeekDialogImpl) }
    }

    override fun setMessage(message: String?) {
        seekDialog.setMessage(message)
    }

    override fun show() {
        seekDialog.show()
    }

    override fun dismiss() {
        seekDialog.dismiss()
    }

    override fun setTitle(title: String?) {
        seekDialog.setTitle(title)
    }

}