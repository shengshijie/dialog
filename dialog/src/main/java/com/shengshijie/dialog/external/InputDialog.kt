package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IInputDialog
import com.shengshijie.dialog.impl.DefaultInputDialogImpl

class InputDialog private constructor(builder: Builder) {

    private var inputDialog: IInputDialog = builder.mInputDialog?:DefaultInputDialogImpl(builder.context)
    var title: String? = builder.mTitle
    var message: String? = builder.mMessage
    var cancelable: Boolean = builder.mCancelable
    var positiveText: String? = builder.mPositiveText
    var negativeText: String? = builder.mNegativeText
    var onClickPositive: (IInputDialog, text: String?) -> Unit = builder.mOnClickPositive
    var onClickNegative: (IInputDialog, text: String?) -> Unit = builder.mOnClickNegative
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        inputDialog.apply {
            setTitle(title)
            setMessage(message)
            setCancelable(cancelable)
            setPositiveButton(positiveText, onClickPositive)
            setNegativeButton(negativeText, onClickNegative)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        inputDialog.dismiss()
    }

    class Builder(val context: Context) {
        internal var mInputDialog: IInputDialog? = null
            private set
        internal var mTitle: String? = null
            private set
        internal var mMessage: String? = null
            private set
        internal var mCancelable: Boolean = true
            private set
        internal var mPositiveText: String? = "Positive"
            private set
        internal var mNegativeText: String? = null
            private set
        internal var mOnClickPositive: (IInputDialog, text: String?) -> Unit = { _, _ -> }
            private set
        internal var mOnClickNegative: (IInputDialog, text: String?) -> Unit = { _, _ -> }
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setInputDialogImpl(inputDialog: IInputDialog): Builder {
            mInputDialog = inputDialog
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

        fun setCancelable(cancelable: Boolean): Builder {
            mCancelable = cancelable
            return this
        }

        fun create(): InputDialog {
            return InputDialog(this)
        }

    }

}