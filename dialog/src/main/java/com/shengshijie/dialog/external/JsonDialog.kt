package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IJsonDialog
import com.shengshijie.dialog.impl.DefaultJsonDialogImpl

class JsonDialog private constructor(builder: Builder) {

    private var jsonDialog: IJsonDialog = builder.mJsonDialog?:DefaultJsonDialogImpl(builder.context)
    var title: String? = builder.mTitle
    var message: String? = builder.mMessage
    var onCopy: (IJsonDialog) -> Unit = builder.mOnCopy
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        jsonDialog.apply {
            setTitle(title)
            setMessage(message)
            setOnCopy(onCopy)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        jsonDialog.dismiss()
    }

    class Builder(val context: Context) {
        internal var mJsonDialog: IJsonDialog? = null
            private set
        internal var mTitle: String? = null
            private set
        internal var mMessage: String? = null
            private set
        internal var mOnCopy: (IJsonDialog) -> Unit = {}
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setJsonDialogImpl(jsonDialog: IJsonDialog): Builder {
            mJsonDialog = jsonDialog
            return this
        }

        fun setOnCopy(listener: (IJsonDialog) -> Unit): Builder {
            mOnCopy = listener
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

        fun create(): JsonDialog {
            return JsonDialog(this)
        }

    }

}