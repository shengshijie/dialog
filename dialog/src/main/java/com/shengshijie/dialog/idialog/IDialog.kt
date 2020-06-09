package com.shengshijie.dialog.idialog

interface IDialog {

    fun setTitle(title: String?)

    fun setMessage(message: String?)

    fun setCancelable(cancelable:Boolean)

    fun show()

    fun dismiss()

    fun setOnDismiss(f: (IDialog) -> Unit)

}