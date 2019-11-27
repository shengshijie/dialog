package com.shengshijie.bright

interface IRadioDialog : IDialog {

    fun setPositiveButton(text: String?, f: (IRadioDialog, index: Int) -> Unit)

    fun setNegativeButton(text: String?, f: (IRadioDialog, index: Int) -> Unit)

}