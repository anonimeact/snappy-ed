package com.anonimeact.snappy_ed

import android.content.Context
import android.os.Environment
import android.util.Base64
import com.jiechic.library.android.snappy.Snappy
import org.json.JSONArray
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

/**
 * Created by anonimeact on 22/02/18.
 * Email didiyuliantos@gmail.com
 */
object Loader {

    fun encryptFile(
        context: Context,
        originFileNameAndExtension: String,
        outputFileNameExtention: String, key: String? = ""
    ) {
        writeDataToStorage(loadPureData(context, originFileNameAndExtension), outputFileNameExtention, key)
    }

    fun writeDataToStorage(
        originData: String,
        outputFileNameAndExtention: String?,
        key: String? = ""
    ) {
        try {
            val encrypted = encrypt(originData, key)
            saveToStorage(outputFileNameAndExtention, encrypted)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun saveToStorage(fileName: String?, data: String) {
        try {
            val sdCard = Environment.getExternalStorageDirectory()
            val dir = File(sdCard.absolutePath + "/snappy-ed")
            dir.mkdirs()
            val file = File(dir, fileName)
            val outputStreamWriter = FileOutputStream(file)
            outputStreamWriter.write(data.toByteArray())
            outputStreamWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun loadEncryptedDataFile(context: Context, pathFile: String, key: String? = ""): String {
        return try {
            val stream = context.assets.open(pathFile)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            decrypt(String(buffer), key)
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    fun loadEncryptedDataFileArray(context: Context, pathFile: String, key: String? = ""): String {
        return try {
            val stream = context.assets.open(pathFile)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            val arrayData: JSONArray? = try {
                JSONArray(decrypt(String(buffer), key))
            } catch (e: Exception) {
                null
            }
            arrayData.toString()
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    fun loadPureData(context: Context, pathFile: String): String {
        return try {
            val stream = context.assets.open(pathFile)
            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }
    }

    fun encrypt(word: String, key: String? = ""): String {
        val snappyBase64 = Base64.encodeToString(Snappy.compress(word), Base64.DEFAULT)
        return EnDec.encrypt(snappyBase64, key) ?: ""
    }

    fun decrypt(word: String, key: String? = ""): String {
        val decodedB64 = EnDec.decrypt(word, key)
        return String(Snappy.uncompress(Base64.decode(decodedB64, Base64.DEFAULT)))
    }
}