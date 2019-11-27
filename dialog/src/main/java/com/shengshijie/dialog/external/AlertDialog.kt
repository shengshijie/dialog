package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IAlertDialog
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.impl.DefaultAlertDialogImpl

class AlertDialog private constructor(builder: Builder) {

    private var alertDialog: IAlertDialog = builder.mAlertDialog ?: DefaultAlertDialogImpl(builder.context)
    var title: String? = builder.mTitle
    var message: String? = builder.mMessage
    var positiveText: String? = builder.mPositiveText
    var negativeText: String? = builder.mNegativeText
    var onClickPositive: (IAlertDialog) -> Unit = builder.mOnClickPositive
    var onClickNegative: (IAlertDialog) -> Unit = builder.mOnClickNegative
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        alertDialog.apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton(positiveText, onClickPositive)
            setNegativeButton(negativeText, onClickNegative)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        alertDialog.dismiss()
    }

    class Builder(val context: Context) {
        internal var mAlertDialog: IAlertDialog? = null
            private set
        internal var mTitle: String? = null
            private set
        internal var mMessage: String? = null
            private set
        internal var mPositiveText: String? = "Positive"
            private set
        internal var mNegativeText: String? = null
            private set
        internal var mOnClickPositive: (IAlertDialog) -> Unit = {}
            private set
        internal var mOnClickNegative: (IAlertDialog) -> Unit = {}
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setAlertDialogImpl(alertDialog: IAlertDialog): Builder {
            mAlertDialog = alertDialog
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