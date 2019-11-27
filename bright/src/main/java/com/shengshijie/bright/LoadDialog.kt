package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DefaultLoadDialogImpl

class LoadDialog private constructor(builder: Builder) {

    private var mLoadDialog: ILoadDialog = DefaultLoadDialogImpl(builder.mContext)
    private var mTitle: String? = builder.mTitle
    private var mMessage: String? = builder.mMessage
    private var mIndeterminate: Boolean = builder.mIndeterminate
    private var mCancelable: Boolean = builder.mCancelable
    private var mProgress: Int = builder.mProgress
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setLoadDialogImpl(loadDialog: ILoadDialog): LoadDialog {
        mLoadDialog = loadDialog
        return this
    }

    fun setTitle(text: String?): LoadDialog {
        mTitle = text
        return this
    }

    fun setMessage(text: String?): LoadDialog {
        mMessage = text
        return this
    }

    fun setIndeterminate(indeterminate: Boolean): LoadDialog {
        mIndeterminate = indeterminate
        return this
    }

    fun setNegativeable(cancelable: Boolean): LoadDialog {
        mCancelable = cancelable
        return this
    }

    fun setProgress(progress: Int): LoadDialog {
        mProgress = progress
        return this
    }

    fun setOnOnDismiss(listener: (IDialog) -> Unit): LoadDialog {
        mOnDismiss = listener
        return this
    }

    fun show() {
        mLoadDialog.apply {
            setTitle(mTitle)
            setMessage(mMessage)
            setOnDismiss(mOnDismiss)
            setIndeterminate(mIndeterminate)
            setNegativeable(mCancelable)
            setProgress(mProgress)
            show()
        }
    }

    fun dismiss() {
        mLoadDialog.dismiss()
    }

    class Builder(context: Context) {

        var mContext: Context = context
        var mTitle: String? = null
        var mMessage: String? = null
        var mIndeterminate: Boolean = true
        var mCancelable: Boolean = false
        var mProgress: Int = 0
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setTitle(text: String?): Builder {
            mTitle = text
            return this
        }

        fun setMessage(text: String?): Builder {
            mMessage = text
            return this
        }

        fun setIndeterminate(indeterminate: Boolean): Builder {
            mIndeterminate = indeterminate
            return this
        }

        fun setCancelable(cancelable: Boolean): Builder {
            mCancelable = cancelable
            return this
        }

        fun setProgress(progress: Int): Builder {
            mProgress = progress
            return this
        }

        fun setOnOnDismiss(listener: (IDialog) -> Unit): Builder {
            mOnDismiss = listener
            return this
        }

        fun create(): LoadDialog {
            return LoadDialog(this)
        }
    }

}