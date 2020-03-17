package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.ICheckDialog
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.impl.DefaultCheckDialogImpl

class CheckDialog private constructor(builder: Builder) {

    private var checkDialog: ICheckDialog =
        builder.mCheckDialog ?: DefaultCheckDialogImpl(
            builder.context,
            builder.mItems,
            builder.mCheckedItems
        )
    var title: String? = builder.mTitle
    var cancelable: Boolean = builder.mCancelable
    var positiveText: String? = builder.mPositiveText
    var negativeText: String? = builder.mNegativeText
    var onClickPositive: (ICheckDialog) -> Unit = builder.mOnClickPositive
    var onClickNegative: (ICheckDialog) -> Unit = builder.mOnClickNegative
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        checkDialog.apply {
            setTitle(title)
            setCancelable(cancelable)
            setPositiveButton(positiveText, onClickPositive)
            setNegativeButton(negativeText, onClickNegative)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        checkDialog.dismiss()
    }

    class Builder(val context: Context) {
        internal var mTitle: String? = null
            private set
        internal var mCancelable: Boolean = true
            private set
        internal var mItems: Array<String>? = null
            private set
        internal var mCheckedItems: BooleanArray = booleanArrayOf()
            private set
        internal var mCheckDialog: ICheckDialog? = null
            private set
        internal var mPositiveText: String? = "Positive"
            private set
        internal var mNegativeText: String? = null
            private set
        internal var mOnClickPositive: (ICheckDialog) -> Unit = { _ -> }
            private set
        internal var mOnClickNegative: (ICheckDialog) -> Unit = { _ -> }
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setCheckDialogImpl(checkDialog: ICheckDialog): Builder {
            mCheckDialog = checkDialog
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

        fun setCancelable(cancelable: Boolean): Builder {
            mCancelable = cancelable
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