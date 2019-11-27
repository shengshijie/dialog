package com.shengshijie.dialog.idialog

interface IRadioDialog : IDialog {

    fun setPositiveButton(text: String?, f: (IRadioDialog, index: Int) -> Unit)

    fun setNegativeButton(text: String?, f: (IRadioDialog, index: Int) -> Unit)

}