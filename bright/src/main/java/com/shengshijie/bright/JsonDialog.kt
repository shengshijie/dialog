package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DefaultJsonDialogImpl

class JsonDialog private constructor(builder: Builder) {

    private var mJsonDialog: IJsonDialog = DefaultJsonDialogImpl(builder.mContext)
    private var mTitle: String? = builder.mTitle
    private var mMessage: String? = builder.mMessage
    private var mOnCopy: (IJsonDialog) -> Unit = builder.mOnCopy
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setJsonDialogImpl(jsonDialog: IJsonDialog): JsonDialog {
        mJsonDialog = jsonDialog
        return this
    }

    fun setOnCopy(listener: (IJsonDialog) -> Unit): JsonDialog {
        mOnCopy = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): JsonDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): JsonDialog {
        mTitle = text
        return this
    }

    fun setMessage(text: String?): JsonDialog {
        mMessage = text
        return this
    }

    fun show() {
        mJsonDialog.apply {
            setTitle(mTitle)
            setMessage(mMessage)
            setOnCopy(mOnCopy)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mJsonDialog.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mMessage: String? = null
        var mOnCopy: (IJsonDialog) -> Unit = {}
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setOnCopy(listener: (IJsonDialog) -> Unit): Builder {
            mOnCopy = listener
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

        fun setMessage(text: String?): Builder {
            mMessage = text
            return this
        }

        fun create(): JsonDialog {
            return JsonDialog(this)
        }

    }

}