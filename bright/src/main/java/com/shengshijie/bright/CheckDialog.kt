package com.shengshijie.bright

import android.content.Context
import com.shengshijie.bright.impl.DefaultCheckDialogImpl

class CheckDialog private constructor(builder: Builder) {

    private var mItems: Array<String>? = builder.mItems
    private var mCheckedItems: BooleanArray = builder.mCheckedItems
    private var mCheckDialog: ICheckDialog = DefaultCheckDialogImpl(builder.mContext, mItems, mCheckedItems)
    private var mTitle: String? = builder.mTitle
    private var mPositiveText: String? = builder.mPositiveText
    private var mNegativeText: String? = builder.mNegativeText
    private var mOnClickPositive: (ICheckDialog) -> Unit = builder.mOnClickPositive
    private var mOnClickNegative: (ICheckDialog) -> Unit = builder.mOnClickNegative
    private var mOnDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun setCheckDialogImpl(checkDialog: ICheckDialog): CheckDialog {
        mCheckDialog = checkDialog
        return this
    }

    fun setPositiveText(text: String?): CheckDialog {
        mPositiveText = text
        return this
    }

    fun setNegativeText(text: String?): CheckDialog {
        mNegativeText = text
        return this
    }

    fun setOnClickPositive(listener: (ICheckDialog) -> Unit): CheckDialog {
        mOnClickPositive = listener
        return this
    }

    fun setOnClickNegative(listener: (ICheckDialog) -> Unit): CheckDialog {
        mOnClickNegative = listener
        return this
    }

    fun setPositive(
            text: String?,
            listener: (ICheckDialog) -> Unit
    ): CheckDialog {
        mPositiveText = text
        mOnClickPositive = listener
        return this
    }

    fun setNegative(
            text: String?,
            listener: (ICheckDialog) -> Unit
    ): CheckDialog {
        mNegativeText = text
        mOnClickPositive = listener
        return this
    }

    fun setOnDismiss(listener: (IDialog) -> Unit): CheckDialog {
        mOnDismiss = listener
        return this
    }

    fun setTitle(text: String?): CheckDialog {
        mTitle = text
        return this
    }

    fun setItems(items: Array<String>?): CheckDialog {
        mItems = items
        return this
    }

    fun setCheckedItems(checkedItems: BooleanArray): CheckDialog {
        mCheckedItems = checkedItems
        return this
    }

    fun show() {
        mCheckDialog.apply {
            setTitle(mTitle)
            setPositiveButton(mPositiveText, mOnClickPositive)
            setNegativeButton(mNegativeText, mOnClickNegative)
            setOnDismiss(mOnDismiss)
            show()
        }
    }

    fun dismiss() {
        mCheckDialog.dismiss()
    }

    class Builder(context: Context) {
        var mContext: Context = context
        var mTitle: String? = null
        var mItems: Array<String>? = null
        var mCheckedItems: BooleanArray = booleanArrayOf()
        var mPositiveText: String? = "Positive"
        var mNegativeText: String? = null
        var mOnClickPositive: (ICheckDialog) -> Unit = { _ -> }
        var mOnClickNegative: (ICheckDialog) -> Unit = { _ -> }
        var mOnDismiss: (IDialog) -> Unit = {}

        fun setPositiveText(text: String?): Builder {
            mPositiveText = text
            return this
        }

        fun setNegativeText(text: String?): Builder {
            mNegativeText = text
            return this
        }

        fun setOnClickPositive(listener: (ICheckDialog) -> Unit): Builder {
            mOnClickPositive = listener
            return this
        }

        fun setOnClickNegative(listener: (ICheckDialog) -> Unit): Builder {
            mOnClickNegative = listener
            return this
        }

        fun setPositive(
                text: String?,
                listener: (ICheckDialog) -> Unit
        ): Builder {
            mPositiveText = text
            mOnClickPositive = listener
            return this
        }

        fun setNegative(
                text: String?,
                listener: (ICheckDialog) -> Unit
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

        fun setCheckedItems(checkedItems: BooleanArray): Builder {
            mCheckedItems = checkedItems
            return this
        }

        fun create(): CheckDialog {
            return CheckDialog(this)
        }

    }

}