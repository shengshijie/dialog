package com.shengshijie.bright

interface ILoadDialog : IDialog{

    fun setIndeterminate(indeterminate:Boolean)

    fun setNegativeable(Negativeable:Boolean)

    fun setProgress(progress:Int)

}