package com.shengshijie.dialog.idialog

interface ICheckDialog : IDialog {

    fun setPositiveButton(text: String?, f: (ICheckDialog) -> Unit)

    fun setNegativeButton(text: String?, f: (ICheckDialog) -> Unit)

}