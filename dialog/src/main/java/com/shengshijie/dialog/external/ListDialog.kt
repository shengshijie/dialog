package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.IListDialog
import com.shengshijie.dialog.impl.DefaultListDialogImpl

class ListDialog private constructor(builder: Builder) {

    private var mListDialog: IListDialog = builder.mListDialog ?: DefaultListDialogImpl(
        builder.context,
        builder.mItems,
        builder.mOnItemClick
    )
    var title: String? = builder.mTitle
    var cancelable: Boolean = builder.mCancelable
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        mListDialog.apply {
            setTitle(title)
            setCancelable(cancelable)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        mListDialog.dismiss()
    }

    class Builder(val context: Context) {
        internal var mTitle: String? = null
            private set
        internal var mCancelable: Boolean = true
            private set
        internal var mItems: Array<String>? = null
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set
        internal var mOnItemClick: (IListDialog, index: Int) -> Unit = { _, _ -> }
            private set
        internal var mListDialog: IListDialog? = null
            private set

        fun setListDialogImpl(listDialog: IListDialog): Builder {
            mListDialog = listDialog
            return this
        }

        fun setOnItemClick(
            listener: (IListDialog, index: Int) -> Unit
        ): Builder {
            mOnItemClick = listener
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

        fun create(): ListDialog {
            return ListDialog(this)
        }

    }

}