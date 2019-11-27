package com.shengshijie.dialog.idialog

interface ILoadDialog : IDialog {

    fun setIndeterminate(indeterminate:Boolean)

    fun setCancelable(cancelable:Boolean)

    fun setProgress(progress:Int)

}