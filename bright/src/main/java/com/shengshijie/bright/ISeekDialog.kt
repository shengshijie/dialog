package com.shengshijie.bright

interface ISeekDialog : IDialog {

    fun setProgress(progress:Int)

    fun setPositiveButton(text: String?, f: (ISeekDialog, index: Int) -> Unit)

    fun setNegativeButton(text: String?, f: (ISeekDialog, index: Int) -> Unit)

}