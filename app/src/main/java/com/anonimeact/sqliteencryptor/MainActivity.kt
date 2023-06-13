package com.anonimeact.sqliteencryptor

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.anonimeact.myutils.DoPermission
import com.anonimeact.myutils.Permissions
import com.anonimeact.snappy_ed.Loader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val permit = arrayOf(Permissions.WRITE_EXTERNAL_STORAGE, Permissions.READ_EXTERNAL_STORAGE)
        DoPermission.checkMultiple(this, permit, 10)

        val encrypted = Loader.encrypt(
            "abcdefghijklmnopqrstuvwxyz1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*(){}",
            "mklopijbhutfdre"
        )
        val decrypted = Loader.decrypt(encrypted, "mklopijbhutfdre")
        Log.d("asdfsd", "asdf E $encrypted")
        Log.d("asdfsd", "asdf D $decrypted")
    }
}