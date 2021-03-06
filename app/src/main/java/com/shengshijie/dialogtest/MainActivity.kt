package com.shengshijie.dialogtest

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.shengshijie.dialog.external.InputDialog
import com.shengshijie.dialog.external.SeekDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        val dialog = InputDialog.Builder(this)
            .setInput("30")
            .create()
        dialog.title = "d"
        dialog.show()
    }
}
