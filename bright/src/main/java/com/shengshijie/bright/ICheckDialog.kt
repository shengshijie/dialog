package com.shengshijie.bright

interface ICheckDialog : IDialog {

    fun setPositiveButton(text: String?, f: (ICheckDialog) -> Unit)

    fun setNegativeButton(text: String?, f: (ICheckDialog) -> Unit)

}