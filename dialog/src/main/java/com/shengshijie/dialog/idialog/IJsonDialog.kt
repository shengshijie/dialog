package com.shengshijie.dialog.idialog

interface IJsonDialog : IDialog {

    fun setOnCopy(f: (IJsonDialog) -> Unit)

}