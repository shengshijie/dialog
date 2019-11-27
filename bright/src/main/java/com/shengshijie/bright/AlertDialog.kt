package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DefaultAlertDialogImpl

class AlertDialog private constructor(builder: Builder) {

    private var mAlertDialog: IAlertDialog? = DefaultAlertDialogImpl(builder.mContext)
    private var mTitle: String? = builder.mTitle
    private var mMessage: String? = builder.mMessage
    private var mPositiveText: String? = builder.mPositiveText
    private var mNegativeText: String? = builder.mNegativeText
    private var mOnClickPositive: (IAlertDialog) -> Unit = builder.mOnClickPositive
    private var mOnClickNegative: (IAlertDialog) -> Unit = builder.mOnClickNegative
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setIAlertDialogImpl(alertDialog: IAlertDialog): AlertDialog {
        mAlertDialog = alertDialog
        return this
    }

    fun setPositiveText(text: String?): AlertDialog {
        mPositiveText = text
        return this
    }

    fun setNegativeText(text: String?): AlertDialog {
        mNegativeText = text
        return this
    }

    fun setOnClickPositive(listener: (IAlertDialog) -> Unit): AlertDialog {
        mOnClickPositive = listener
        return this
    }

    fun setOnClickNegative(listener: (IAlertDialog) -> Unit): AlertDialog {
        mOnClickNegative = listener
        return this
    }

    fun setPositive(text: String?, listener: (IAlertDialog) -> Unit): AlertDialog {
        mPositiveText = text
        mOnClickPositive = listener
        return this
    }

    fun setNegative(text: String?, listener: (IAlertDialog) -> Unit): AlertDialog {
        mNegativeText = text
        mOnClickPositive = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): AlertDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): AlertDialog {
        mTitle = text
        return this
    }

    fun setMessage(text: String?): AlertDialog {
        mMessage = text
        return this
    }

    fun show() {
        mAlertDialog?.apply {
            setTitle(mTitle)
            setMessage(mMessage)
            setPositiveButton(mPositiveText, mOnClickPositive)
            setNegativeButton(mNegativeText, mOnClickNegative)
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
        var mOnClickPositive: (IAlertDialog) -> Unit = {}
        var mOnClickNegative: (IAlertDialog) -> Unit = {}
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setPositiveText(text: String?): Builder {
            mPositiveText = text
            return this
        }

        fun setNegativeText(text: String?): Builder {
            mNegativeText = text
            return this
        }

        fun setOnClickPositive(listener: (IAlertDialog) -> Unit): Builder {
            mOnClickPositive = listener
            return this
        }

        fun setOnClickNegative(listener: (IAlertDialog) -> Unit): Builder {
            mOnClickNegative = listener
            return this
        }

        fun setPositive(text: String?, listener: (IAlertDialog) -> Unit): Builder {
            mPositiveText = text
            mOnClickPositive = listener
            return this
        }

        fun setNegative(text: String?, listener: (IAlertDialog) -> Unit): Builder {
            mNegativeText = text
            mOnClickPositive = listener
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

        fun create(): AlertDialog {
            return AlertDialog(this)
        }

    }

}