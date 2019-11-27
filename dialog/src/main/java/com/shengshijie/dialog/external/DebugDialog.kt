package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.ICheckDialog
import com.shengshijie.dialog.idialog.IDebugDialog
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.impl.DefaultDebugDialogImpl

class DebugDialog private constructor(builder: Builder) {

    private var debugDialog: IDebugDialog = builder.mDebugDialog?:DefaultDebugDialogImpl(builder.context)
    var title: String? = builder.mTitle
    var message: String? = builder.mMessage
    var positiveText: String? = builder.mPositiveText
    var negativeText: String? = builder.mNegativeText
    var onClickPositive: (IDebugDialog) -> Unit = builder.mOnClickPositive
    var onClickNegative: (IDebugDialog) -> Unit = builder.mOnClickNegative
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss
    var onCopyMessage: (IDialog) -> Unit = builder.mOnCopyMessage

    fun show() {
        debugDialog.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveText, onClickPositive)
            setNegativeButton(negativeText, onClickNegative)
            setOnCopyMessage(onCopyMessage)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        debugDialog.dismiss()
    }

    class Builder(val context: Context) {
        internal var mDebugDialog: IDebugDialog? = null
            private set
        internal var mTitle: String? = null
            private set
        internal var mMessage: String? = null
            private set
        internal var mPositiveText: String? = "Positive"
            private set
        internal var mNegativeText: String? = null
            private set
        internal var mOnClickPositive: (IDebugDialog) -> Unit = {}
            private set
        internal var mOnClickNegative: (IDebugDialog) -> Unit = {}
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set
        internal var mOnCopyMessage: (IDialog) -> Unit = {}
            private set

        fun setCheckDialogImpl(debugDialog: IDebugDialog): Builder {
            mDebugDialog = debugDialog
            return this
        }

        fun setPositiveText(text: String?): Builder {
            mPositiveText = text
            return this
        }

        fun setNegativeText(text: String?): Builder {
            mNegativeText = text
            return this
        }

        fun setOnClickPositive(listener: (IDebugDialog) -> Unit): Builder {
            mOnClickPositive = listener
            return this
        }

        fun setOnClickNegative(listener: (IDebugDialog) -> Unit): Builder {
            mOnClickNegative = listener
            return this
        }

        fun setPositive(text: String?, listener: (IDebugDialog) -> Unit): Builder {
            mPositiveText = text
            mOnClickPositive = listener
            return this
        }

        fun setNegative(text: String?, listener: (IDebugDialog) -> Unit): Builder {
            mNegativeText = text
            mOnClickPositive = listener
            return this
        }

        fun setOnCopyMessage(listener: (IDialog) -> Unit): Builder {
            mOnCopyMessage = listener
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

        fun create(): DebugDialog {
            return DebugDialog(this)
        }

    }

}