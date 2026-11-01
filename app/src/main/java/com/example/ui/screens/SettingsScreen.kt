package com.example.ui.screens

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AddPhotoAlternate
import androidx.compose.material.icons.filled.Backup
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Restore
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Verified
import com.example.ui.components.CurrencySelectionDialog
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.R
import com.example.data.model.AppCurrency
import com.example.data.model.CurrencyCatalog
import com.example.data.model.StoreProfile
import com.example.ui.LakuKasirViewModel
import com.example.ui.theme.EmeraldGreenDark
import com.example.ui.theme.EmeraldGreenPrimary
import com.example.util.BackupRestoreHelper
import com.example.util.FormatUtils
import com.example.util.LakuStrings
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: LakuKasirViewModel,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val currentProfile by viewModel.storeProfile.collectAsState()

    var storeName by remember(currentProfile) { mutableStateOf(currentProfile.storeName) }
    var ownerName by remember(currentProfile) { mutableStateOf(currentProfile.ownerName) }
    var address by remember(currentProfile) { mutableStateOf(currentProfile.address) }
    var phone by remember(currentProfile) { mutableStateOf(currentProfile.phone) }
    var receiptNotes by remember(currentProfile) { mutableStateOf(currentProfile.receiptNotes) }
    var logoUri by remember(currentProfile) { mutableStateOf(currentProfile.logoUri) }
    var selectedTheme by remember(currentProfile) { mutableStateOf(currentProfile.themeMode) }
    var selectedLanguage by remember(currentProfile) { mutableStateOf(currentProfile.language) }
    var selectedCurrencyCode by remember(currentProfile) { mutableStateOf(currentProfile.currencyCode) }

    var showAboutDialog by remember { mutableStateOf(false) }
    var saveSuccessMessage by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()
    val products by viewModel.allProducts.collectAsState()
    val transactions by viewModel.allTransactions.collectAsState()
    val expenses by viewModel.allExpenses.collectAsState()

    val restoreFileLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        if (uri != null) {
            coroutineScope.launch {
                BackupRestoreHelper.restoreBackupFromJsonUri(
                    context = context,
                    uri = uri,
                    onSuccess = { pCount, tCount ->
                        Toast.makeText(context, "Berhasil memulihkan $pCount produk & $tCount transaksi!", Toast.LENGTH_LONG).show()
                    },
                    onError = { err ->
                        Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                    }
                )
            }
        }
    }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            val permanentPath = com.example.util.ImageUtils.saveImageToInternalStorage(context, uri, "store_logo")
            if (permanentPath != null) {
                logoUri = permanentPath
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(LakuStrings.get("settings", selectedLanguage), fontWeight = FontWeight.Bold) },
                navigationIcon = {
                    IconButton(onClick = onBack, modifier = Modifier.testTag("settings_back_button")) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            // Edit Profil Toko & Upload Logo
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = EmeraldGreenPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("store_profile", selectedLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    // Logo Toko Upload Area
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(72.dp)
                                .clip(CircleShape)
                                .background(EmeraldGreenPrimary.copy(alpha = 0.1f)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (logoUri != null) {
                                AsyncImage(
                                    model = ImageRequest.Builder(context)
                                        .data(logoUri)
                                        .error(R.drawable.logo_laku_kasir)
                                        .fallback(R.drawable.logo_laku_kasir)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "Logo Toko",
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier.fillMaxSize()
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.logo_laku_kasir),
                                    contentDescription = "Logo Toko Default",
                                    modifier = Modifier.size(44.dp)
                                )
                            }
                        }

                        Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                            Button(
                                onClick = {
                                    photoPickerLauncher.launch(
                                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier.testTag("upload_logo_button")
                            ) {
                                Icon(imageVector = Icons.Default.AddPhotoAlternate, contentDescription = null, tint = Color.White)
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = if (logoUri != null) LakuStrings.get("change_logo", selectedLanguage) else LakuStrings.get("upload_logo", selectedLanguage),
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            if (logoUri != null) {
                                OutlinedButton(
                                    onClick = { logoUri = null },
                                    shape = RoundedCornerShape(10.dp)
                                ) {
                                    Text(LakuStrings.get("delete_logo", selectedLanguage), fontSize = 11.sp, color = Color.Red)
                                }
                            }
                        }
                    }

                    HorizontalDivider(thickness = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)

                    // Nama Toko
                    OutlinedTextField(
                        value = storeName,
                        onValueChange = { storeName = it },
                        label = { Text(LakuStrings.get("store_name", selectedLanguage)) },
                        placeholder = { Text("Contoh: Toko Berkah Jaya") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = EmeraldGreenPrimary) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("store_name_input"),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Nama Pemilik
                    OutlinedTextField(
                        value = ownerName,
                        onValueChange = { ownerName = it },
                        label = { Text(LakuStrings.get("owner_name", selectedLanguage)) },
                        placeholder = { Text("Contoh: Rafline") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Person, contentDescription = null, tint = EmeraldGreenPrimary) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("owner_name_input"),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Alamat Toko
                    OutlinedTextField(
                        value = address,
                        onValueChange = { address = it },
                        label = { Text(LakuStrings.get("store_address", selectedLanguage)) },
                        placeholder = { Text("Contoh: Jl. Merdeka No. 45, Jakarta") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = EmeraldGreenPrimary) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("address_input"),
                        maxLines = 2,
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Nomor Telepon / WA Toko
                    OutlinedTextField(
                        value = phone,
                        onValueChange = { phone = it },
                        label = { Text(LakuStrings.get("whatsapp_no", selectedLanguage)) },
                        placeholder = { Text("Contoh: 081234567890") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = null, tint = EmeraldGreenPrimary) },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("phone_input"),
                        singleLine = true,
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Catatan Struk / Kuitansi
                    OutlinedTextField(
                        value = receiptNotes,
                        onValueChange = { receiptNotes = it },
                        label = { Text(LakuStrings.get("receipt_notes", selectedLanguage)) },
                        placeholder = { Text("Contoh: Terima kasih telah berbelanja! Semoga berkah.") },
                        leadingIcon = { Icon(imageVector = Icons.Default.Receipt, contentDescription = null, tint = EmeraldGreenPrimary) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("receipt_notes_input"),
                        minLines = 2,
                        maxLines = 4,
                        shape = RoundedCornerShape(10.dp)
                    )
                }
            }

            // Ganti Tema (Terang, Gelap, Sesuai Sistem)
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Palette, contentDescription = null, tint = EmeraldGreenPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("theme", selectedLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        val themes = listOf(
                            "system" to LakuStrings.get("theme_system", selectedLanguage),
                            "light" to LakuStrings.get("theme_light", selectedLanguage),
                            "dark" to LakuStrings.get("theme_dark", selectedLanguage)
                        )

                        themes.forEach { (mode, label) ->
                            val isSelected = selectedTheme == mode
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) EmeraldGreenPrimary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                border = BorderStroke(1.dp, if (isSelected) EmeraldGreenPrimary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        selectedTheme = mode
                                        viewModel.setThemeMode(mode)
                                    }
                                    .testTag("theme_chip_$mode")
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 10.dp, horizontal = 4.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 12.sp,
                                        maxLines = 1,
                                        softWrap = false,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Bahasa (Bahasa Indonesia & English)
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Language, contentDescription = null, tint = EmeraldGreenPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("language", selectedLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        val languages = listOf(
                            "id" to "🇮🇩 Indonesia",
                            "en" to "🇬🇧 English"
                        )

                        languages.forEach { (code, label) ->
                            val isSelected = selectedLanguage == code
                            Surface(
                                shape = RoundedCornerShape(10.dp),
                                color = if (isSelected) EmeraldGreenPrimary else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                                border = BorderStroke(1.dp, if (isSelected) EmeraldGreenPrimary else MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable {
                                        selectedLanguage = code
                                        viewModel.setLanguage(code)
                                    }
                                    .testTag("lang_chip_$code")
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 12.dp, horizontal = 6.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = label,
                                        fontSize = 13.sp,
                                        maxLines = 1,
                                        softWrap = false,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Cadangkan Data ke Google Drive atau Kirim ke WhatsApp
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Backup, contentDescription = null, tint = EmeraldGreenPrimary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("backup_restore", selectedLanguage),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = EmeraldGreenDark
                        )
                    }

                    Text(
                        text = LakuStrings.get("backup_restore_desc", selectedLanguage),
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Spacer(modifier = Modifier.height(2.dp))

                    // Tombol Cadangkan ke Google Drive
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val file = BackupRestoreHelper.createBackupJson(
                                    context, currentProfile, products, transactions, expenses
                                )
                                if (file != null) {
                                    BackupRestoreHelper.backupToGoogleDrive(context, file)
                                } else {
                                    Toast.makeText(context, "Gagal membuat file cadangan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1A73E8)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("backup_drive_button")
                    ) {
                        Icon(imageVector = Icons.Default.CloudUpload, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("backup_google_drive", selectedLanguage),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }

                    // Tombol Kirim File ke WhatsApp
                    Button(
                        onClick = {
                            coroutineScope.launch {
                                val file = BackupRestoreHelper.createBackupJson(
                                    context, currentProfile, products, transactions, expenses
                                )
                                if (file != null) {
                                    BackupRestoreHelper.shareBackupToWhatsApp(context, file)
                                } else {
                                    Toast.makeText(context, "Gagal membuat file cadangan", Toast.LENGTH_SHORT).show()
                                }
                            }
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF25D366)),
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(48.dp)
                            .testTag("backup_whatsapp_button")
                    ) {
                        Icon(imageVector = Icons.Default.Send, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("backup_whatsapp", selectedLanguage),
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            fontSize = 13.sp
                        )
                    }

                    // Tombol Pulihkan Data dari File
                    OutlinedButton(
                        onClick = {
                            restoreFileLauncher.launch("application/json")
                        },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("restore_backup_button")
                    ) {
                        Icon(imageVector = Icons.Default.Restore, contentDescription = null, tint = EmeraldGreenDark)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = LakuStrings.get("restore_json", selectedLanguage),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = EmeraldGreenDark
                        )
                    }
                }
            }

            // Tombol Simpan Pengaturan
            Button(
                onClick = {
                    val updated = currentProfile.copy(
                        storeName = storeName.trim(),
                        ownerName = ownerName.trim(),
                        address = address.trim(),
                        phone = phone.trim(),
                        receiptNotes = receiptNotes.trim(),
                        logoUri = logoUri,
                        themeMode = selectedTheme,
                        language = selectedLanguage,
                        currencyCode = selectedCurrencyCode
                    )
                    viewModel.updateStoreProfile(updated)
                    saveSuccessMessage = true
                    Toast.makeText(context, LakuStrings.get("settings_saved", selectedLanguage), Toast.LENGTH_SHORT).show()
                },
                colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
                shape = RoundedCornerShape(14.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .testTag("save_settings_button")
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = Color.White)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = LakuStrings.get("save_settings", selectedLanguage),
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    color = Color.White
                )
            }

            if (saveSuccessMessage) {
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = EmeraldGreenPrimary.copy(alpha = 0.15f),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = LakuStrings.get("settings_saved", selectedLanguage),
                        color = EmeraldGreenDark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        modifier = Modifier.padding(12.dp)
                    )
                }
            }

            // TENTANG APLIKASI (CLICKABLE ITEM DI BAGIAN BAWAH)
            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                border = BorderStroke(1.dp, EmeraldGreenPrimary.copy(alpha = 0.25f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showAboutDialog = true }
                    .testTag("about_app_button")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(EmeraldGreenPrimary.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = null,
                                tint = EmeraldGreenDark,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                        Column {
                            Text(
                                text = LakuStrings.get("about_app", selectedLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = LakuStrings.get("about_app_subtitle", selectedLanguage),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Buka Tentang Aplikasi",
                        tint = EmeraldGreenDark
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // DIALOG TENTANG APLIKASI (MODAL DIALOG)
    if (showAboutDialog) {
        AlertDialog(
            onDismissRequest = { showAboutDialog = false },
            confirmButton = {
                Button(
                    onClick = { showAboutDialog = false },
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreenPrimary),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .testTag("close_about_dialog_button")
                ) {
                    Text(
                        text = LakuStrings.get("close", selectedLanguage),
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 15.sp
                    )
                }
            },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(EmeraldGreenPrimary.copy(alpha = 0.15f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            tint = EmeraldGreenDark,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                    Text(
                        text = LakuStrings.get("about_app", selectedLanguage),
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }
            },
            text = {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Nama Aplikasi & Versi
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = "Laku Kasir Pro",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 18.sp,
                                color = EmeraldGreenDark
                            )
                            Text(
                                text = LakuStrings.get("version", selectedLanguage),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = EmeraldGreenPrimary
                        ) {
                            Text(
                                text = "v1.1",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    // Lisensi Full Version Resmi
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFE8F5E9),
                        border = BorderStroke(1.5.dp, EmeraldGreenPrimary),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Verified,
                                contentDescription = null,
                                tint = EmeraldGreenDark,
                                modifier = Modifier.size(26.dp)
                            )
                            Column {
                                Text(
                                    text = LakuStrings.get("full_version_title", selectedLanguage),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 13.sp,
                                    color = EmeraldGreenDark
                                )
                                Text(
                                    text = LakuStrings.get("full_version_desc", selectedLanguage),
                                    fontSize = 11.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }

                    // Developer Information
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.45f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text(
                                text = LakuStrings.get("developer_label", selectedLanguage),
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = LakuStrings.get("developer_value", selectedLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    // Keterangan Fitur Aplikasi
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.25f),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Text(
                                text = LakuStrings.get("features_label", selectedLanguage),
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp,
                                color = EmeraldGreenDark
                            )
                            Text(
                                text = LakuStrings.get("features_list", selectedLanguage),
                                fontSize = 11.sp,
                                lineHeight = 17.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            },
            shape = RoundedCornerShape(20.dp)
        )
    }
}
