package com.shengshijie.dialogtest

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.shengshijie.dialog.external.InputDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        val dialog = InputDialog.Builder(this)
            .setTitle("hehe")
            .setMessage("dd")
            .setCancelable(false)
            .setOnClickPositive { dialog, text ->
                Toast.makeText(this, text, Toast.LENGTH_LONG).show()
                dialog.dismiss()
            }
            .create()
            .show()

    }
}
