package com.shengshijie.dialog.idialog

interface ISeekDialog : IDialog {

    fun setProgress(progress:Int)

    fun setPositiveButton(text: String?, f: (ISeekDialog, index: Int) -> Unit)

    fun setNegativeButton(text: String?, f: (ISeekDialog, index: Int) -> Unit)

}