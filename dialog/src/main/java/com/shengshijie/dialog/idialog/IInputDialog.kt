package com.shengshijie.dialog.idialog

interface IInputDialog : IDialog {

    fun setPositiveButton(text: String?, f: (IInputDialog, text: String?) -> Unit)

    fun setNegativeButton(text: String?,  f: (IInputDialog, text: String?) -> Unit)

}