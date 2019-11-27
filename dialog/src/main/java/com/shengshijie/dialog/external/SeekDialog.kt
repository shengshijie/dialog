package com.shengshijie.dialog.external

import android.content.Context
import com.shengshijie.dialog.idialog.IDialog
import com.shengshijie.dialog.idialog.ISeekDialog
import com.shengshijie.dialog.impl.DefaultSeekDialogImpl

class SeekDialog private constructor(builder: Builder) {

    private var mSeekDialog: ISeekDialog =
        builder.mSeekDialog ?: DefaultSeekDialogImpl(builder.context, builder.mDefaultProgress)
    var defaultProgress: Int = builder.mDefaultProgress
    var title: String? = builder.mTitle
    var positiveText: String? = builder.mPositiveText
    var negativeText: String? = builder.mNegativeText
    var onClickPositive: (ISeekDialog, index: Int) -> Unit = builder.mOnClickPositive
    var onClickNegative: (ISeekDialog, index: Int) -> Unit = builder.mOnClickNegative
    var onDismiss: (IDialog) -> Unit = builder.mOnDismiss

    fun show() {
        mSeekDialog.apply {
            setTitle(title)
            setProgress(defaultProgress)
            setPositiveButton(positiveText, onClickPositive)
            setNegativeButton(negativeText, onClickNegative)
            setOnDismiss(onDismiss)
            show()
        }
    }

    fun dismiss() {
        mSeekDialog.dismiss()
    }

    class Builder(val context: Context) {

        internal var mTitle: String? = null
            private set
        internal var mDefaultProgress: Int = 0
            private set
        internal var mSeekDialog: ISeekDialog? = null
            private set
        internal var mPositiveText: String? = "Positive"
            private set
        internal var mNegativeText: String? = null
            private set
        internal var mOnClickPositive: (ISeekDialog, index: Int) -> Unit = { _, _ -> }
            private set
        internal var mOnClickNegative: (ISeekDialog, index: Int) -> Unit = { _, _ -> }
            private set
        internal var mOnDismiss: (IDialog) -> Unit = {}
            private set

        fun setSeekDialogImpl(seekDialog: ISeekDialog): Builder {
            mSeekDialog = seekDialog
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