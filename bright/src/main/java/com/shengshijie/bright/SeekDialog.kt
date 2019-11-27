package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.SeekDialogImpl

class SeekDialog private constructor(builder: Builder) {

    private var mDefaultProgress: Int = builder.mDefaultProgress
    private var mSeekDialog: ISeekDialog? = SeekDialogImpl(builder.mContext, mDefaultProgress)
    private var mTitle: String? = builder.mTitle
    private var mPositiveText: String? = builder.mPositiveText
    private var mNegativeText: String? = builder.mNegativeText
    private var mOnClickPositive: (ISeekDialog, index: Int) -> Unit = builder.mOnClickPositive
    private var mOnClickNegative: (ISeekDialog, index: Int) -> Unit = builder.mOnClickNegative
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setPositiveText(text: String?): SeekDialog {
        mPositiveText = text
        return this
    }

    fun setNegativeText(text: String?): SeekDialog {
        mNegativeText = text
        return this
    }

    fun setOnClickPositive(listener: (ISeekDialog, index: Int) -> Unit): SeekDialog {
        mOnClickPositive = listener
        return this
    }

    fun setOnClickNegative(listener: (ISeekDialog, index: Int) -> Unit): SeekDialog {
        mOnClickNegative = listener
        return this
    }

    fun setPositive(
            text: String?,
            listener: (ISeekDialog, index: Int) -> Unit
    ): SeekDialog {
        mPositiveText = text
        mOnClickPositive = listener
        return this
    }

    fun setNegative(
            text: String?,
            listener: (ISeekDialog, selectedIndex: Int) -> Unit
    ): SeekDialog {
        mNegativeText = text
        mOnClickPositive = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): SeekDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): SeekDialog {
        mTitle = text
        return this
    }

    fun setDefaultProgress(defaultProgress: Int): SeekDialog {
        mDefaultProgress = defaultProgress
        return this
    }

    fun show() {
        mSeekDialog?.apply {
            setTitle(mTitle)
            setProgress(mDefaultProgress)
            setPositiveButton(mPositiveText, mOnClickPositive)
            setNegativeButton(mNegativeText, mOnClickNegative)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mSeekDialog?.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mDefaultProgress: Int = 0
        var mPositiveText: String? = "Positive"
        var mNegativeText: String? = null
        var mOnClickPositive: (ISeekDialog, index: Int) -> Unit = { _, _ -> }
        var mOnClickNegative: (ISeekDialog, index: Int) -> Unit = { _, _ -> }
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setPositiveText(text: String?): Builder {
            mPositiveText = text
            return this
        }

        fun setNegativeText(text: String?): Builder {
            mNegativeText = text
            return this
        }

        fun setOnClickPositive(listener: (ISeekDialog, index: Int) -> Unit): Builder {
            mOnClickPositive = listener
            return this
        }

        fun setOnClickNegative(listener: (ISeekDialog, index: Int) -> Unit): Builder {
            mOnClickNegative = listener
            return this
        }

        fun setPositive(
                text: String?,
                listener: (ISeekDialog, index: Int) -> Unit
        ): Builder {
            mPositiveText = text
            mOnClickPositive = listener
            return this
        }

        fun setNegative(
                text: String?,
                listener: (ISeekDialog, selectedIndex: Int) -> Unit
        ): Builder {
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

        fun setDefaultProgress(defaultProgress: Int): Builder {
            mDefaultProgress = defaultProgress
            return this
        }

        fun create(): SeekDialog {
            return SeekDialog(this)
        }

    }

}