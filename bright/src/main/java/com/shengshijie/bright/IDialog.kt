package com.shengshijie.bright

interface IDialog {

    fun setTitle(title: String?)

    fun setMessage(message: String?)

    fun show()

    fun dismiss()

    fun setOnDismiss(f: (IDialog) -> Unit)

}