package com.shengshijie.dialog.idialog

interface IAlertDialog : IDialog {

    fun setPositiveButton(text: String?, f: (IAlertDialog) -> Unit)

    fun setNegativeButton(text: String?,  f: (IAlertDialog) -> Unit)

}