package com.shengshijie.dialog.idialog

interface IDebugDialog : IDialog {

    fun setOnCopyMessage(f: (IDebugDialog) -> Unit)

    fun setPositiveButton(text: String?, f: (IDebugDialog) -> Unit)

    fun setNegativeButton(text: String?,  f: (IDebugDialog) -> Unit)

}