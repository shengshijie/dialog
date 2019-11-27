package com.shengshijie.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.shengshijie.bright.InputDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test(view: View) {
        val dialog = InputDialog.Builder(this)
            .setTitle("title")
            .setMessage("message")
            .setOnClickPositive { _, s -> Log.i("TAG", s ?: "") }
            .create()
            .show()
    }
}
