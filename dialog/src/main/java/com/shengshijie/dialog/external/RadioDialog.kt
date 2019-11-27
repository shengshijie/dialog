package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IRadioDialog
import com.shengshijie.dialog.impl.DefaultRadioDialogImpl

class RadioDialog private constructor(builder: Builder) {

    private var mRadioDialog: IRadioDialog = builder.mRadioDialog ?: DefaultRadioDialogImpl(
        builder.context,
        builder.mItems,
        builder.mDefaultIndex
    )
    var title: String? = builder.mTitle
    var positiveText: String? = builder.mPositiveText
    var negativeText: String? = builder.mNegativeText
    var onClickPositive: (IRadioDialog, index: Int) -> Unit = builder.mOnClickPositive
    var onClickNegative: (IRadioDialog, index: Int) -> Unit = builder.mOnClickNegative
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        mRadioDialog.apply {
            setTitle(title)
            setPositiveButton(positiveText, onClickPositive)
            setNegativeButton(negativeText, onClickNegative)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        mRadioDialog.dismiss()
    }

    class Builder(val context: Context) {

        internal var mTitle: String? = null
            private set
        internal var mItems: Array<String>? = null
            private set
        internal var mDefaultIndex: Int = -1
            private set
        internal var mPositiveText: String? = "Positive"
            private set
        internal var mNegativeText: String? = null
            private set
        internal var mRadioDialog: IRadioDialog? = null
            private set
        internal var mOnClickPositive: (IRadioDialog, index: Int) -> Unit = { _, _ -> }
            private set
        internal var mOnClickNegative: (IRadioDialog, index: Int) -> Unit = { _, _ -> }
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setRadioDialogImpl(radioDialog: IRadioDialog): Builder {
            mRadioDialog = radioDialog
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