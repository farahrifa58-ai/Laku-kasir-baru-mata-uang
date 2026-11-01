package com.example.util

object LakuStrings {
    fun get(key: String, lang: String = "id"): String {
        val isEn = lang.equals("en", ignoreCase = true)
        return when (key) {
            // General & App Branding
            "app_title" -> "Laku Kasir Pro"
            "by_rafline" -> "by Rafline Dev"
            "version" -> if (isEn) "Version 1.1" else "Versi 1.1"
            "save" -> if (isEn) "Save" else "Simpan"
            "cancel" -> if (isEn) "Cancel" else "Batal"
            "delete" -> if (isEn) "Delete" else "Hapus"
            "edit" -> if (isEn) "Edit" else "Edit"
            "close" -> if (isEn) "Close" else "Tutup"
            "optional" -> if (isEn) "Optional" else "Opsional"
            "back" -> if (isEn) "Back" else "Kembali"

            // Navigation
            "nav_pos" -> if (isEn) "Cashier" else "Kasir"
            "nav_products" -> if (isEn) "Products" else "Produk"
            "nav_history" -> if (isEn) "History" else "Riwayat"
            "nav_dashboard" -> if (isEn) "Dashboard" else "Dasbor"
            "settings" -> if (isEn) "Settings" else "Pengaturan"

            // Settings Screen
            "store_profile" -> if (isEn) "Store Profile" else "Profil Toko"
            "store_name" -> if (isEn) "Store Name" else "Nama Toko"
            "owner_name" -> if (isEn) "Store Owner" else "Nama Pemilik Toko"
            "store_address" -> if (isEn) "Store Address" else "Alamat Toko"
            "whatsapp_no" -> if (isEn) "Store WhatsApp Number" else "Nomor WhatsApp Toko"
            "receipt_notes" -> if (isEn) "Receipt Footer Note" else "Catatan di Bagian Struk"
            "upload_logo" -> if (isEn) "Upload Store Logo" else "Upload Logo Toko"
            "change_logo" -> if (isEn) "Change Store Logo" else "Ganti Logo Toko"
            "delete_logo" -> if (isEn) "Remove" else "Hapus"
            "theme" -> if (isEn) "Theme" else "Tema"
            "theme_system" -> if (isEn) "System" else "Sistem"
            "theme_light" -> if (isEn) "Light" else "Terang"
            "theme_dark" -> if (isEn) "Dark" else "Gelap"
            "language" -> if (isEn) "Language" else "Bahasa"
            "backup_restore" -> if (isEn) "Backup & Restore Store Data" else "Cadangkan & Pulihkan Data Toko"
            "backup_restore_desc" -> if (isEn) "Safely backup product catalog, stock, transaction history, and store expenses." else "Simpan seluruh katalog produk, stok, riwayat transaksi, dan biaya operasional toko ke file cadangan yang aman."
            "backup_google_drive" -> if (isEn) "Backup to Google Drive" else "Cadangkan ke Google Drive"
            "backup_whatsapp" -> if (isEn) "Send Backup to WhatsApp" else "Kirim File Cadangan ke WhatsApp"
            "restore_json" -> if (isEn) "Restore Data from Backup File (JSON)" else "Pulihkan Data dari File Cadangan (JSON)"
            "save_settings" -> if (isEn) "Save Settings" else "Simpan Pengaturan"
            "settings_saved" -> if (isEn) "✓ Store settings saved successfully!" else "✓ Pengaturan toko berhasil disimpan!"
            "about_app" -> if (isEn) "About Application" else "Tentang Aplikasi"
            "about_app_subtitle" -> if (isEn) "Laku Kasir Pro • Version 1.1 (Full Version)" else "Laku Kasir Pro • Versi 1.1 (Full Version)"
            "full_version_title" -> if (isEn) "LAKU KASIR PRO (FULL VERSION)" else "LAKU KASIR PRO (FULL VERSION)"
            "full_version_desc" -> if (isEn) "All features active • Unlimited Products & Transactions • Status: OFFICIAL" else "Semua fitur aktif • Unlimited Produk & Transaksi • Status: RESMI"
            "developer_label" -> if (isEn) "Developer:" else "Pengembang / Developer:"
            "developer_value" -> if (isEn) "Cashier & POS Application by Rafline Dev" else "Aplikasi Kasir & POS by Rafline Dev"
            "features_label" -> if (isEn) "Features & Description:" else "Fitur & Keterangan:"
            "features_list" -> if (isEn) {
                "• Multi-Currency (USD, Southeast Asia, East Asia, Europe, Africa, & Russia)\n" +
                "• Fast Cashier Transactions & Automatic Change Calculator\n" +
                "• Bluetooth Thermal Printing & Share via WhatsApp/PDF\n" +
                "• Unlimited Product, Category, & Inventory Management\n" +
                "• Sales Reports, Net Profit/Loss, & Store Expenses\n" +
                "• Secure Backup & Restore to Google Drive & WhatsApp"
            } else {
                "• Multi-Mata Uang Internasional (Dolar, Asia Tenggara, Asia, Eropa, Afrika, & Rusia)\n" +
                "• Transaksi Kasir Cepat & Hitung Kembalian Otomatis\n" +
                "• Cetak Struk Bluetooth & Bagikan via WhatsApp/PDF\n" +
                "• Manajemen Produk, Kategori, & Stok Tanpa Batas\n" +
                "• Laporan Penjualan, Laba Bersih, & Pengeluaran Toko\n" +
                "• Cadangkan Data ke Google Drive & WhatsApp"
            }

            // POS / Cashier Screen
            "search_product_barcode" -> if (isEn) "Search product or barcode..." else "Cari produk atau barcode..."
            "all" -> if (isEn) "All" else "Semua"
            "no_products" -> if (isEn) "No Products Yet" else "Belum Ada Produk"
            "no_products_desc" -> if (isEn) "Your product catalog is empty. Please add new products to start cashier transactions." else "Katalog produk Anda masih kosong. Silakan tambahkan produk baru untuk mulai bertransaksi di kasir."
            "add_product_btn" -> if (isEn) "+ Add Product" else "+ Tambah Produk"
            "cart" -> if (isEn) "Order Cart" else "Keranjang Pesanan"
            "clear_cart" -> if (isEn) "Clear" else "Kosongkan"
            "manual_discount" -> if (isEn) "Manual Discount" else "Diskon Manual"
            "customer_name" -> if (isEn) "Customer Name" else "Nama Pelanggan"
            "customer_phone" -> if (isEn) "Customer WhatsApp" else "Nomor WhatsApp Pelanggan"
            "subtotal" -> if (isEn) "Subtotal" else "Subtotal"
            "discount" -> if (isEn) "Discount" else "Diskon"
            "total_payment" -> if (isEn) "Total Payment" else "Total Pembayaran"
            "checkout" -> if (isEn) "Proceed to Payment" else "Lanjut Pembayaran"

            // Checkout Screen
            "payment" -> if (isEn) "Payment" else "Pembayaran"
            "payment_method" -> if (isEn) "Payment Method" else "Metode Pembayaran"
            "cash" -> if (isEn) "Cash" else "Tunai"
            "transfer" -> if (isEn) "Bank Transfer" else "Transfer Bank"
            "qris" -> if (isEn) "QRIS / E-Wallet" else "QRIS"
            "debt" -> if (isEn) "Unpaid / Credit" else "Kasbon / Belum Lunas"
            "cash_received" -> if (isEn) "Cash Received" else "Uang Diterima"
            "exact_cash" -> if (isEn) "Exact Cash" else "Uang Pas"
            "change" -> if (isEn) "Change Due" else "Kembalian"
            "complete_payment" -> if (isEn) "Complete & Save Transaction" else "Selesaikan & Simpan Transaksi"
            "paid_status" -> if (isEn) "Paid" else "Lunas"

            // Products Screen
            "search_product_placeholder" -> if (isEn) "Search name, barcode, or supplier..." else "Cari nama, barcode, atau supplier..."
            "change_currency" -> if (isEn) "• Change" else "• Ganti"
            "product_count_suffix" -> if (isEn) "Products" else "Produk"
            "stock" -> if (isEn) "Stock" else "Stok Barang"
            "cost_price" -> if (isEn) "Cost Price (HPP)" else "Harga Modal (HPP)"
            "selling_price" -> if (isEn) "Selling Price" else "Harga Jual"

            // Add / Edit Product Screen
            "add_product" -> if (isEn) "Add Product" else "Tambah Produk"
            "edit_product" -> if (isEn) "Edit Product" else "Edit Produk"
            "product_name" -> if (isEn) "Product Name" else "Nama Produk"
            "category" -> if (isEn) "Category" else "Kategori"
            "unit" -> if (isEn) "Unit" else "Satuan"
            "barcode" -> if (isEn) "Barcode / SKU" else "Kode / Barcode"
            "generate_barcode" -> if (isEn) "Auto Barcode" else "Buat Barcode Otomatis"
            "change_country" -> if (isEn) "Change Country" else "Ganti Negara"
            "stock_unlimited" -> if (isEn) "Unlimited Stock (Services/Digital)" else "Stok Tidak Terbatas (Unlimited)"
            "min_stock_alert" -> if (isEn) "Low Stock Warning Limit" else "Batas Peringatan Stok Menipis"
            "supplier_name" -> if (isEn) "Supplier Name" else "Nama Supplier"
            "supplier_phone" -> if (isEn) "Supplier WhatsApp" else "Nomor WhatsApp Supplier"
            "save_product" -> if (isEn) "Save Product" else "Simpan Produk"

            // Dashboard Screen
            "period_today" -> if (isEn) "Today" else "Hari Ini"
            "period_week" -> if (isEn) "7 Days" else "7 Hari"
            "period_month" -> if (isEn) "This Month" else "Bulan Ini"
            "period_all" -> if (isEn) "All Time" else "Semua"
            "revenue" -> if (isEn) "Sales Revenue" else "Omzet Penjualan"
            "net_profit" -> if (isEn) "Estimated Net Profit" else "Estimasi Laba Bersih"
            "cost_sold" -> if (isEn) "Cost of Goods Sold (COGS)" else "Modal Terjual (HPP)"
            "gross_profit" -> if (isEn) "Gross Profit" else "Laba Kotor"
            "store_expense" -> if (isEn) "Store Expenses" else "Biaya Pengeluaran Toko"
            "stock_asset" -> if (isEn) "Stock Asset Value" else "Nilai Aset Stok"
            "record_expense" -> if (isEn) "Record Store Expense" else "Catat Pengeluaran Toko"
            "transactions_suffix" -> if (isEn) "Transactions" else "Transaksi"
            "surplus_clean" -> if (isEn) "Net Surplus" else "Surplus Bersih"
            "deficit" -> if (isEn) "Deficit" else "Defisit"
            "cost_sold_subtitle" -> if (isEn) "Cost of sold goods" else "Harga modal barang"
            "gross_profit_subtitle" -> if (isEn) "Revenue - COGS" else "Omzet - HPP"

            // History Screen
            "search_history" -> if (isEn) "Search receipt code or customer..." else "Cari kode transaksi atau pelanggan..."
            "filter_all" -> if (isEn) "All" else "Semua"
            "filter_paid" -> if (isEn) "Paid" else "Lunas"
            "filter_debt" -> if (isEn) "Unpaid / Debt" else "Kasbon"
            "view_receipt" -> if (isEn) "View Receipt" else "Lihat Struk"

            // Receipt Screen
            "receipt" -> if (isEn) "Transaction Receipt" else "Struk Transaksi"
            "share" -> if (isEn) "Share" else "Bagikan"
            "receipt_actions" -> if (isEn) "Print & Share Receipt" else "Aksi Cetak & Bagikan Struk"
            "print_receipt" -> if (isEn) "Print Receipt (Thermal / Printer / PDF)" else "Cetak Struk (Thermal / Printer / PDF)"
            "download_pdf" -> if (isEn) "Download PDF" else "Unduh PDF"
            "download_image" -> if (isEn) "Download Image" else "Unduh Foto"
            "send_whatsapp" -> if (isEn) "Send Receipt to WhatsApp" else "Kirim Struk ke WhatsApp"
            "new_transaction" -> if (isEn) "New Transaction" else "Transaksi Baru"
            "back_to_home" -> if (isEn) "Back to Home" else "Kembali ke Beranda"
            "owner" -> if (isEn) "Owner" else "Pemilik"
            "created_with" -> if (isEn) "Created with Laku Kasir Pro" else "Dibuat dengan Laku Kasir Pro"

            else -> key
        }
    }
}
