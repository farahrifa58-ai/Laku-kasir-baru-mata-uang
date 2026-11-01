package com.example.ui.components

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Stars
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.util.LicenseManager

@Composable
fun LicenseActivationDialog(
    onDismiss: () -> Unit,
    onActivated: () -> Unit,
    sellerPhone: String = LicenseManager.SELLER_WHATSAPP
) {
    val context = LocalContext.current
    var inputKey by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var successActivated by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Stars,
                    contentDescription = null,
                    tint = Color(0xFFF57C00),
                    modifier = Modifier.size(24.dp)
                )
                Text(
                    text = if (successActivated) "Aktivasi Berhasil!" else "Aktivasi Lisensi PRO",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                if (successActivated) {
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
                        border = BorderStroke(1.dp, EmeraldGreenPrimary),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(14.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = EmeraldGreenDark,
                                modifier = Modifier.size(36.dp)
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            Text(
                                text = "Selamat! Aplikasi Anda Kini Telah Menjadi Versi PRO Resmi.",
                                fontWeight = FontWeight.Bold,
                                color = EmeraldGreenDark,
                                textAlign = TextAlign.Center,
                                fontSize = 14.sp
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Semua batasan produk, transaksi, dan ekspor telah dibuka tanpa batas (unlimited).",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                } else {
                    Text(
                        text = "Aplikasi saat ini berjalan dalam Mode Trial (Uji Coba). Masukkan kode lisensi yang diberikan oleh penjual untuk membuka fitur penuh.",
                        fontSize = 13.sp,
                        color = Color.DarkGray
                    )

                    Surface(
                        color = Color(0xFFFFF8E1),
                        shape = RoundedCornerShape(8.dp),
                        border = BorderStroke(1.dp, Color(0xFFFFE082))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "Keuntungan Versi PRO:",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = Color(0xFFE65100)
                            )
                            Text("✓ Input produk tanpa batas (Unlimited)", fontSize = 11.sp)
                            Text("✓ Transaksi kasir tanpa batas (Unlimited)", fontSize = 11.sp)
                            Text("✓ Struk bersih tanpa watermark demo", fontSize = 11.sp)
                            Text("✓ Backup & restore data Google Drive / WA", fontSize = 11.sp)
                        }
                    }

                    OutlinedTextField(
                        value = inputKey,
                        onValueChange = {
                            inputKey = it
                            errorMessage = null
                        },
                        label = { Text("Kode Lisensi PRO") },
                        placeholder = { Text("Contoh: LAKU-PRO-2026") },
                        leadingIcon = {
                            Icon(Icons.Default.Key, contentDescription = null, tint = EmeraldGreenPrimary)
                        },
                        singleLine = true,
                        isError = errorMessage != null,
                        supportingText = {
                            if (errorMessage != null) {
                                Text(errorMessage ?: "", color = Color.Red, fontSize = 11.sp)
                            } else {
                                Text("Dapatkan kode lisensi resmi dari penjual", fontSize = 11.sp)
                            }
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)

                    // Tombol Hubungi Penjual via WhatsApp
                    OutlinedButton(
                        onClick = {
                            LicenseManager.contactSellerForLicense(context, sellerPhone)
                        },
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = EmeraldGreenDark)
                    ) {
                        Icon(Icons.Default.Phone, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Beli Lisensi via WhatsApp", fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        },
        confirmButton = {
            if (successActivated) {
                Button(
                    onClick = {
                        onActivated()
                        onDismiss()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary)
                ) {
                    Text("Selesai")
                }
            } else {
                Button(
                    onClick = {
                        if (inputKey.isBlank()) {
                            errorMessage = "Silakan masukkan kode lisensi"
                            return@Button
                        }
                        val success = LicenseManager.activateWithKey(inputKey)
                        if (success) {
                            successActivated = true
                            Toast.makeText(context, "Lisensi PRO Berhasil Diaktifkan!", Toast.LENGTH_LONG).show()
                        } else {
                            errorMessage = "Kode lisensi tidak valid atau kadaluarsa. Silakan periksa kembali."
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary)
                ) {
                    Text("Aktivasi Sekarang")
                }
            }
        },
        dismissButton = {
            if (!successActivated) {
                TextButton(onClick = onDismiss) {
                    Text("Nanti Saja")
                }
            }
        }
    )
}
