package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DebugDialogImpl

class DebugDialog private constructor(builder: Builder) {

    private var mAlertDialog: IDebugDialog? = DebugDialogImpl(builder.mContext)
    private var mTitle: String? = builder.mTitle
    private var mMessage: String? = builder.mMessage
    private var mPositiveText: String? = builder.mPositiveText
    private var mNegativeText: String? = builder.mNegativeText
    private var mOnClickPositive: (IDebugDialog) -> Unit = builder.mOnClickPositive
    private var mOnClickNegative: (IDebugDialog) -> Unit = builder.mOnClickNegative
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss
    private var mOnCopyMessage: (IDialog) -> Unit = builder.mOnCopyMessage

    fun setPositiveText(text: String?): DebugDialog {
        mPositiveText = text
        return this
    }

    fun setNegativeText(text: String?): DebugDialog {
        mNegativeText = text
        return this
    }

    fun setOnClickPositive(listener: (IDebugDialog) -> Unit): DebugDialog {
        mOnClickPositive = listener
        return this
    }

    fun setOnClickNegative(listener: (IDebugDialog) -> Unit): DebugDialog {
        mOnClickNegative = listener
        return this
    }

    fun setPositive(text: String?, listener: (IDebugDialog) -> Unit): DebugDialog {
        mPositiveText = text
        mOnClickPositive = listener
        return this
    }

    fun setNegative(text: String?, listener: (IDebugDialog) -> Unit): DebugDialog {
        mNegativeText = text
        mOnClickPositive = listener
        return this
    }

    fun setOnCopyMessage(listener: (IDialog) -> Unit): DebugDialog {
        mOnCopyMessage = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): DebugDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): DebugDialog {
        mTitle = text
        return this
    }

    fun setMessage(text: String?): DebugDialog {
        mMessage = text
        return this
    }

    fun show() {
        mAlertDialog?.apply {
            setTitle(mTitle)
            setMessage(mMessage)
            setPositiveButton(mPositiveText, mOnClickPositive)
            setNegativeButton(mNegativeText, mOnClickNegative)
            setOnCopyMessage(mOnCopyMessage)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mAlertDialog?.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mMessage: String? = null
        var mPositiveText: String? = "Positive"
        var mNegativeText: String? = null
        var mOnClickPositive: (IDebugDialog) -> Unit = {}
        var mOnClickNegative: (IDebugDialog) -> Unit = {}
        var mOnDismiss: (IDialog) -> Unit = {}
        var mOnCopyMessage: (IDialog) -> Unit = {}

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