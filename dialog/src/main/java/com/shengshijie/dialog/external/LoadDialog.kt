package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.ILoadDialog
import com.shengshijie.dialog.impl.DefaultLoadDialogImpl

class LoadDialog private constructor(builder: Builder) {

    private var mLoadDialog: ILoadDialog =
        builder.mLoadDialog ?: DefaultLoadDialogImpl(builder.context)
    var title: String? = builder.mTitle
    var message: String? = builder.mMessage
    var indeterminate: Boolean = builder.mIndeterminate
    var cancelable: Boolean = builder.mCancelable
    var progress: Int = builder.mProgress
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        mLoadDialog.apply {
            setTitle(title)
            setMessage(message)
            setOnDismiss(onDismiss)
            setIndeterminate(indeterminate)
            setCancelable(cancelable)
            setProgress(progress)
            show()
        }
    }

    fun dismiss() {
        mLoadDialog.dismiss()
    }

    class Builder(val context: Context) {

        internal var mLoadDialog: ILoadDialog? = null
            private set
        internal var mTitle: String? = null
            private set
        internal var mMessage: String? = null
            private set
        internal var mIndeterminate: Boolean = true
            private set
        internal var mCancelable: Boolean = true
            private set
        internal var mProgress: Int = 0
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setLoadDialogImpl(loadDialog: ILoadDialog): Builder {
            mLoadDialog = loadDialog
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