package com.shengshijie.bright

interface IJsonDialog : IDialog {

    fun setOnCopy(f: (IJsonDialog) -> Unit)

}