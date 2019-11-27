package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DefaultInputDialogImpl

class InputDialog private constructor(builder: Builder) {

    private var mInputDialog: IInputDialog = DefaultInputDialogImpl(builder.mContext)
    private var mTitle: String? = builder.mTitle
    private var mMessage: String? = builder.mMessage
    private var mPositiveText: String? = builder.mPositiveText
    private var mNegativeText: String? = builder.mNegativeText
    private var mOnClickPositive: (IInputDialog, text: String?) -> Unit = builder.mOnClickPositive
    private var mOnClickNegative: (IInputDialog, text: String?) -> Unit = builder.mOnClickNegative
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setInputDialogImpl(inputDialog: IInputDialog): InputDialog {
        mInputDialog = inputDialog
        return this
    }

    fun setPositiveText(text: String?): InputDialog {
        mPositiveText = text
        return this
    }

    fun setNegativeText(text: String?): InputDialog {
        mNegativeText = text
        return this
    }

    fun setOnClickPositive(listener: (IInputDialog, text: String?) -> Unit): InputDialog {
        mOnClickPositive = listener
        return this
    }

    fun setOnClickNegative(listener: (IInputDialog, text: String?) -> Unit): InputDialog {
        mOnClickNegative = listener
        return this
    }

    fun setPositive(text: String?, listener: (IInputDialog, text: String?) -> Unit): InputDialog {
        mPositiveText = text
        mOnClickPositive = listener
        return this
    }

    fun setNegative(text: String?, listener: (IInputDialog, text: String?) -> Unit): InputDialog {
        mNegativeText = text
        mOnClickPositive = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): InputDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): InputDialog {
        mTitle = text
        return this
    }

    fun setMessage(text: String?): InputDialog {
        mMessage = text
        return this
    }

    fun show() {
        mInputDialog.apply {
            setTitle(mTitle)
            setMessage(mMessage)
            setPositiveButton(mPositiveText, mOnClickPositive)
            setNegativeButton(mNegativeText, mOnClickNegative)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mInputDialog.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mMessage: String? = null
        var mPositiveText: String? = "Positive"
        var mNegativeText: String? = null
        var mOnClickPositive: (IInputDialog, text: String?) -> Unit = { _, _ -> }
        var mOnClickNegative: (IInputDialog, text: String?) -> Unit = { _, _ -> }
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setPositiveText(text: String?): Builder {
            mPositiveText = text
            return this
        }

        fun setNegativeText(text: String?): Builder {
            mNegativeText = text
            return this
        }

        fun setOnClickPositive(listener: (IInputDialog, text: String?) -> Unit): Builder {
            mOnClickPositive = listener
            return this
        }

        fun setOnClickNegative(listener: (IInputDialog, text: String?) -> Unit): Builder {
            mOnClickNegative = listener
            return this
        }

        fun setPositive(text: String?, listener: (IInputDialog, text: String?) -> Unit): Builder {
            mPositiveText = text
            mOnClickPositive = listener
            return this
        }

        fun setNegative(text: String?, listener: (IInputDialog, text: String?) -> Unit): Builder {
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

        fun create(): InputDialog {
            return InputDialog(this)
        }

    }

}