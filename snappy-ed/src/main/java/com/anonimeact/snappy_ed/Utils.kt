package com.anonimeact.snappy_ed

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.content.pm.Signature
import android.os.Build
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.security.MessageDigest
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate

object Utils {
    fun getLocalCertificate(
        context: Context,
        isPureCertificate: Boolean = false,
        isLength15: Boolean = true
    ): String {
        val signatures: Array<Signature> = getSignature(context)
        val cert: ByteArray = signatures[0].toByteArray()
        val input: InputStream = ByteArrayInputStream(cert)
        var cf: CertificateFactory? = null
        try {
            cf = CertificateFactory.getInstance("X509")
        } catch (e: CertificateException) {
            e.printStackTrace()
        }
        var c: X509Certificate? = null
        try {
            c = cf?.generateCertificate(input) as X509Certificate
        } catch (e: CertificateException) {
            e.printStackTrace()
        }
        val md: MessageDigest = MessageDigest.getInstance("SHA1")
        val publicKey: ByteArray = md.digest(c?.publicKey?.encoded!!)
        val hexString = StringBuffer()
        for (i in publicKey.indices) {
            val appendString = Integer.toHexString(
                0xFF and publicKey[i]
                    .toInt()
            )
            if (appendString.length == 1) hexString.append("0")
            hexString.append(appendString)
        }
        return if (isPureCertificate) hexString.toString()
        else {
            val certificate = setUnixCharacter(
                hexString.toString() + context.packageName.lowercase() + context.packageName.uppercase()
            ).filter { it.isLetterOrDigit() }
            return if (isLength15) certificate.substring(0, 15) else certificate
        }
    }

    fun setUnixCharacter(clearCertificate: String): String {
        var tempString = ""
        clearCertificate.forEach { c -> if (!tempString.contains(c)) tempString += c }
        return tempString
    }

    private fun getSignature(context: Context): Array<Signature> {
        val packageName = context.packageName
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            val packageInfo = context.packageManager.getPackageInfoCompact(
                packageName, PackageManager.GET_SIGNING_CERTIFICATES
            )
            packageInfo.signingInfo.apkContentsSigners
        } else {
            val packageInfo = context.packageManager.getPackageInfoCompact(
                packageName, PackageManager.GET_SIGNATURES
            )
            packageInfo.signatures
        }
    }
}

fun PackageManager.getPackageInfoCompact(
    packageName: String,
    flags: Int = 0
): PackageInfo =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU)
        getPackageInfo(packageName, PackageManager.PackageInfoFlags.of(flags.toLong()))
    else getPackageInfo(packageName, flags)
