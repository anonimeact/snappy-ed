package com.anonimeact.endec

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.anonimeact.snappy_ed.Loader

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val encrypted = Loader.encrypt(
            "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*(){}",
            "mklopijbhutfdre"
        )
        val decrypted = Loader.decrypt(encrypted, "mklopijbhutfdre")
        Log.d("MainActivity", "encrypt $encrypted")
        Log.d("MainActivity", "decrypt $decrypted")
    }
}