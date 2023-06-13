//package com.anonimeact.sqliteencryptor.jadwalsholat
//
//import android.content.Context
//import android.os.AsyncTask
//import android.os.Bundle
//import android.util.Base64
//import android.util.Log
//import androidx.appcompat.app.AppCompatActivity
//import com.anonimeact.myutils.DoPermission
//import com.anonimeact.myutils.Permissions
//import com.anonimeact.sqliteencryptor.Loader.exeLoadOnly
//import com.anonimeact.sqliteencryptor.Loader.writeIntoDataFromData
//import com.google.gson.Gson
//import com.google.gson.reflect.TypeToken
//import java.io.UnsupportedEncodingException
//import java.security.InvalidKeyException
//import java.security.NoSuchAlgorithmException
//import javax.crypto.BadPaddingException
//import javax.crypto.Cipher
//import javax.crypto.IllegalBlockSizeException
//import javax.crypto.NoSuchPaddingException
//import javax.crypto.spec.SecretKeySpec
//
///**
// * Created by anonimeact on 2019-10-21.
// * Email didiyuliantos@gmail.com
// */
//class JadwalSolatKemenag : AppCompatActivity() {
//    var listLokasi = ArrayList<PojoLokasi>()
//    var listLokasiCopiedModified = ArrayList<PojoLokasi>()
//    var TAG = ".JadwalSolatKemenag"
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        mContext = this
//
//        /*        byte b = (byte) 104;
//        byte b2 = (byte) 98;
//        byte b3 = (byte) 105;
//        byte b4 = (byte) 115;
//        byte b5 = (byte) 108;
//        byte[] a = new byte[]{(byte) 109, (byte) 114, b, (byte) 97, b2, b3, b2, b3, b4, (byte) 117, b4, b, (byte) 111, b5, b5, b3};
//
//        try {
//            Log.d(TAG, "onCreate: exeYear " + new String(a, "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }*/listLokasi = Gson().fromJson(
//            exeLoadOnly(this, "jadwalsholat/location.json"),
//            object : TypeToken<List<PojoLokasi?>?>() {}.type
//        )
//        listLokasiCopiedModified = Gson().fromJson(
//            exeLoadOnly(this, "jadwalsholat/location.json"),
//            object : TypeToken<List<PojoLokasi?>?>() {}.type
//        )
//        DoPermission.check(this, Permissions.WRITE_EXTERNAL_STORAGE, 100)
//        /**
//         * Decrypt From Aes, and encrypt to self encriptions
//         */
//
////        listLokasiCopiedModified.addAll(listLokasi);
//        for (index in listLokasiCopiedModified.indices) {
//            listLokasiCopiedModified[index].setId(index + 1)
//        }
//
////        for (PojoLokasi data : listLokasi){
////            Log.d(TAG, "onCreate: " + "id = " + data.getId() + " name = " + data.getCt());
////        }
//
////        Log.d(TAG, "onCreate: " + String.valueOf(2019).substring(2));
//
////        Loader.writeIntoDataFromData(this, new Gson().toJson(listLokasiCopiedModified), "lokasikabupaten.txt");
////        Loader.writeIntoDataFromFile(this, "jadwalsholat/testing.txt", "hasiltesting.txt");
//        exeYear()
//    }
//
//    private fun exeYear() {
////        Log.d(TAG, "exeYear: " + Loader.exeLoadOnly(this, "jadwalsholat/10012019.txt"));
////        Log.d(TAG, "exeYear: " + Loader.exeDecryptStr(this, "jadwalsholat/10012019.txt"));
//        for (index in 2019..2019) {
//            for (indexList in listLokasi.indices) {
//                val dataMentah = listLokasi[indexList]
//                val dataModif = listLokasiCopiedModified[indexList]
//                val link =
//                    "jadwalsholat/" + index + "/" + dataMentah.getId() + "_" + index + ".json"
//                val newName = dataModif.getId().toString() + index.toString().substring(2) + ".txt"
//
////                Log.d(TAG, "exeYear: " + link + " as " + newName);
//                DoData(link, newName).execute()
//            }
//        }
//
////        new DoData("jadwalsholat/2019/1001_2019.json", "aaaaa.txt").execute();
//    }
//
//    private inner class DoData internal constructor(var link: String, var newName: String) :
//        AsyncTask<String?, Void?, String?>() {
//        override fun onPreExecute() {
//            super.onPreExecute()
//        }
//
//        protected override fun doInBackground(vararg strings: String): String {
////            Log.d(TAG, "doInBackground: " + link);
////            Log.d(TAG, "doInBackground: " + newName);
//            try {
//                val instance = Cipher.getInstance("AES")
//                instance.init(2, SecretKeySpec(key, "AES"))
//                val doFinal = instance.doFinal(
//                    Base64.decode(
//                        exeLoadOnly(
//                            this!!, link
//                        ), 0
//                    )
//                )
//                //                Log.d(TAG, "doInBackground: " + new String(doFinal, "UTF-8"));
//                /**
//                 * Write and encrypt
//                 */
//                writeIntoDataFromData(this@JadwalSolatKemenag, String(doFinal, Charsets.UTF_8), newName)
//                /**
//                 * Only Write
//                 */
////                Loader.writeToFile("mentah_" + newName, new String(doFinal, "UTF-8"), this);
//            } catch (e: NoSuchAlgorithmException) {
//                e.printStackTrace()
//            } catch (e: NoSuchPaddingException) {
//                e.printStackTrace()
//            } catch (e: InvalidKeyException) {
//                e.printStackTrace()
//            } catch (e: BadPaddingException) {
//                e.printStackTrace()
//            } catch (e: IllegalBlockSizeException) {
//                e.printStackTrace()
//            } catch (e: UnsupportedEncodingException) {
//                e.printStackTrace()
//            }
//            return "$link Done"
//        }
//
//        protected override fun onPostExecute(s: String) {
//            super.onPostExecute(s)
//            Log.d(TAG, "onPostExecute: $s")
//
////            if (year < 2051){
////                new DoData().execute();
////            }
//        }
//    }
//
//    companion object {
//        private val key: ByteArray
//
//        init {
//            val b = 104.toByte()
//            val b2 = 98.toByte()
//            val b3 = 105.toByte()
//            val b4 = 115.toByte()
//            val b5 = 108.toByte()
//            key = byteArrayOf(
//                109.toByte(),
//                114.toByte(),
//                com.anonimeact.sqliteencryptor.jadwalsholat.b,
//                97.toByte(),
//                com.anonimeact.sqliteencryptor.jadwalsholat.b2,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b3,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b2,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b3,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b4,
//                117.toByte(),
//                com.anonimeact.sqliteencryptor.jadwalsholat.b4,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b,
//                111.toByte(),
//                com.anonimeact.sqliteencryptor.jadwalsholat.b5,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b5,
//                com.anonimeact.sqliteencryptor.jadwalsholat.b3
//            )
//        }
//    }
//}