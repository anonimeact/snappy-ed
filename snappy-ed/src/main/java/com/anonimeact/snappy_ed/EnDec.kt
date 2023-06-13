package com.anonimeact.snappy_ed

/**
 * Created by anonimeact on 21/02/18.
 * Email didiyuliantos@gmail.com
 */
object EnDec {
    external fun encrypt(x: String?, key: String? = ""): String?
    external fun decrypt(x: String?, key: String? = ""): String?
    init {
        System.loadLibrary("ed-lib")
    }
}