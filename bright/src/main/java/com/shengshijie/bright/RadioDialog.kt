package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.RadioDialogImpl

class RadioDialog private constructor(builder: Builder) {

    private var mItems: Array<String>? = builder.mItems
    private var mDefaultIndex: Int = builder.mDefaultIndex
    private var mRadioDialog: IRadioDialog? = RadioDialogImpl(builder.mContext, mItems, mDefaultIndex)
    private var mTitle: String? = builder.mTitle
    private var mPositiveText: String? = builder.mPositiveText
    private var mNegativeText: String? = builder.mNegativeText
    private var mOnClickPositive: (IRadioDialog, index: Int) -> Unit = builder.mOnClickPositive
    private var mOnClickNegative: (IRadioDialog, index: Int) -> Unit = builder.mOnClickNegative
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setPositiveText(text: String?): RadioDialog {
        mPositiveText = text
        return this
    }

    fun setNegativeText(text: String?): RadioDialog {
        mNegativeText = text
        return this
    }

    fun setOnClickPositive(listener: (IRadioDialog, index: Int) -> Unit): RadioDialog {
        mOnClickPositive = listener
        return this
    }

    fun setOnClickNegative(listener: (IRadioDialog, index: Int) -> Unit): RadioDialog {
        mOnClickNegative = listener
        return this
    }

    fun setPositive(
            text: String?,
            listener: (IRadioDialog, index: Int) -> Unit
    ): RadioDialog {
        mPositiveText = text
        mOnClickPositive = listener
        return this
    }

    fun setNegative(
            text: String?,
            listener: (IRadioDialog, selectedIndex: Int) -> Unit
    ): RadioDialog {
        mNegativeText = text
        mOnClickPositive = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): RadioDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): RadioDialog {
        mTitle = text
        return this
    }

    fun show() {
        mRadioDialog?.apply {
            setTitle(mTitle)
            setPositiveButton(mPositiveText, mOnClickPositive)
            setNegativeButton(mNegativeText, mOnClickNegative)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mRadioDialog?.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mItems: Array<String>? = null
        var mDefaultIndex: Int = -1
        var mPositiveText: String? = "Positive"
        var mNegativeText: String? = null
        var mOnClickPositive: (IRadioDialog, index: Int) -> Unit = { _, _ -> }
        var mOnClickNegative: (IRadioDialog, index: Int) -> Unit = { _, _ -> }
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setPositiveText(text: String?): Builder {
            mPositiveText = text
            return this
        }

        fun setNegativeText(text: String?): Builder {
            mNegativeText = text
            return this
        }

        fun setOnClickPositive(listener: (IRadioDialog, index: Int) -> Unit): Builder {
            mOnClickPositive = listener
            return this
        }

        fun setOnClickNegative(listener: (IRadioDialog, index: Int) -> Unit): Builder {
            mOnClickNegative = listener
            return this
        }

        fun setPositive(
                text: String?,
                listener: (IRadioDialog, index: Int) -> Unit
        ): Builder {
            mPositiveText = text
            mOnClickPositive = listener
            return this
        }

        fun setNegative(
                text: String?,
                listener: (IRadioDialog, selectedIndex: Int) -> Unit
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

        fun setItems(items: Array<String>?): Builder {
            mItems = items
            return this
        }

        fun setDefaultIndex(defaultIndex: Int): Builder {
            mDefaultIndex = defaultIndex
            return this
        }

        fun create(): RadioDialog {
            return RadioDialog(this)
        }

    }

}