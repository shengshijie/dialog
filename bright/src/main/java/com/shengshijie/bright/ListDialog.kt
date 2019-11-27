package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DefaultListDialogImpl

class ListDialog private constructor(builder: Builder) {

    private var mItems: Array<String>? = builder.mItems
    private var mOnItemClick: (IListDialog, index: Int) -> Unit = builder.mOnItemClick
    private var mListDialog: IListDialog = DefaultListDialogImpl(builder.mContext, mItems, mOnItemClick)
    private var mTitle: String? = builder.mTitle
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setListDialogImpl(listDialog: IListDialog): ListDialog {
        mListDialog = listDialog
        return this
    }

    fun setOnItemClick(
        listener: (IListDialog, index: Int) -> Unit
    ): ListDialog {
        mOnItemClick = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): ListDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): ListDialog {
        mTitle = text
        return this
    }

    fun setItems(items: Array<String>?): ListDialog {
        mItems = items
        return this
    }

    fun show() {
        mListDialog.apply {
            setTitle(mTitle)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mListDialog.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mItems: Array<String>? = null
        var mOnDismiss: (IDialog) -> Unit = {}
        var mOnItemClick: (IListDialog, index: Int) -> Unit = { _, _ -> }

        fun setOnItemClick(
            listener: (IListDialog, index: Int) -> Unit
        ): Builder {
            mOnItemClick = listener
            return this
        }

        fun setOnDismiss(listener: (IDialog) -> Unit): Builder {
            mOnDismiss = listener
            return this
        }

        fun setTitle(text: String?): Builder {
            mTitle = text
            return this
        }

        fun setItems(items: Array<String>?): Builder {
            mItems = items
            return this
        }

        fun create(): ListDialog {
            return ListDialog(this)
        }

    }

}