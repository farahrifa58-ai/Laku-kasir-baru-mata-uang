package com.example.util

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * LicenseManager: Versi PRO Penuh (Trial/Percobaan telah dihapus sesuai permintaan)
 * Seluruh fitur kasir aktif tanpa batas transaksi dan tanpa batas produk.
 */
object LicenseManager {

    const val SELLER_WHATSAPP = "081234567890"

    private val _isProFlow = MutableStateFlow(true)
    val isProFlow: StateFlow<Boolean> = _isProFlow.asStateFlow()

    fun init(context: Context) {
        _isProFlow.value = true
    }

    fun isProActivated(): Boolean = true

    fun activateWithKey(inputKey: String): Boolean = true

    fun resetToTrial() {
        // Mode trial sudah dihapus secara permanen, aplikasi selalu Full Version
        _isProFlow.value = true
    }

    fun contactSellerForLicense(context: Context, sellerPhone: String = SELLER_WHATSAPP) {
        val targetPhone = if (sellerPhone.isNotBlank()) sellerPhone else SELLER_WHATSAPP
        var cleanPhone = targetPhone.replace(Regex("[^0-9]"), "")
        if (cleanPhone.startsWith("0")) {
            cleanPhone = "62" + cleanPhone.substring(1)
        }
        val message = "Halo Rafline Dev, saya ingin info tentang aplikasi Laku Kasir Pro. Terima kasih!"
        try {
            val uri = Uri.parse("https://wa.me/$cleanPhone?text=${Uri.encode(message)}")
            val intent = Intent(Intent.ACTION_VIEW, uri)
            context.startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(context, "Gagal membuka WhatsApp: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }
}
