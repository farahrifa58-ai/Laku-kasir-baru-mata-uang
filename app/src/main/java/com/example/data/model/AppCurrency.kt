package com.example.data.model

data class AppCurrency(
    val code: String,
    val symbol: String,
    val name: String,
    val country: String,
    val region: String,
    val flag: String,
    val decimalDigits: Int = 0,
    val isSymbolPrefix: Boolean = true
)

object CurrencyCatalog {

    val REGIONS = listOf("Semua", "Asia Tenggara", "Asia", "Dolar & Global", "Eropa & Rusia", "Afrika")

    val ALL_CURRENCIES = listOf(
        // INDONESIA & ASIA TENGGARA
        AppCurrency(
            code = "IDR",
            symbol = "Rp",
            name = "Rupiah",
            country = "Indonesia",
            region = "Asia Tenggara",
            flag = "🇮🇩",
            decimalDigits = 0
        ),
        AppCurrency(
            code = "MYR",
            symbol = "RM",
            name = "Ringgit",
            country = "Malaysia",
            region = "Asia Tenggara",
            flag = "🇲🇾",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "SGD",
            symbol = "S$",
            name = "Singapore Dollar",
            country = "Singapura",
            region = "Asia Tenggara",
            flag = "🇸🇬",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "THB",
            symbol = "฿",
            name = "Baht",
            country = "Thailand",
            region = "Asia Tenggara",
            flag = "🇹🇭",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "PHP",
            symbol = "₱",
            name = "Peso",
            country = "Filipina",
            region = "Asia Tenggara",
            flag = "🇵🇭",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "VND",
            symbol = "₫",
            name = "Dong",
            country = "Vietnam",
            region = "Asia Tenggara",
            flag = "🇻🇳",
            decimalDigits = 0
        ),
        AppCurrency(
            code = "BND",
            symbol = "B$",
            name = "Brunei Dollar",
            country = "Brunei Darussalam",
            region = "Asia Tenggara",
            flag = "🇧🇳",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "KHR",
            symbol = "៛",
            name = "Riel",
            country = "Kamboja",
            region = "Asia Tenggara",
            flag = "🇰🇭",
            decimalDigits = 0
        ),

        // DOLAR & GLOBAL
        AppCurrency(
            code = "USD",
            symbol = "$",
            name = "US Dollar",
            country = "Amerika Serikat",
            region = "Dolar & Global",
            flag = "🇺🇸",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "AUD",
            symbol = "A$",
            name = "Australian Dollar",
            country = "Australia",
            region = "Dolar & Global",
            flag = "🇦🇺",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "CAD",
            symbol = "C$",
            name = "Canadian Dollar",
            country = "Kanada",
            region = "Dolar & Global",
            flag = "🇨🇦",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "NZD",
            symbol = "NZ$",
            name = "New Zealand Dollar",
            country = "Selandia Baru",
            region = "Dolar & Global",
            flag = "🇳🇿",
            decimalDigits = 2
        ),

        // ASIA
        AppCurrency(
            code = "JPY",
            symbol = "¥",
            name = "Yen",
            country = "Jepang",
            region = "Asia",
            flag = "🇯🇵",
            decimalDigits = 0
        ),
        AppCurrency(
            code = "KRW",
            symbol = "₩",
            name = "Won",
            country = "Korea Selatan",
            region = "Asia",
            flag = "🇰🇷",
            decimalDigits = 0
        ),
        AppCurrency(
            code = "CNY",
            symbol = "¥",
            name = "Yuan / Renminbi",
            country = "China",
            region = "Asia",
            flag = "🇨🇳",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "HKD",
            symbol = "HK$",
            name = "Hong Kong Dollar",
            country = "Hong Kong",
            region = "Asia",
            flag = "🇭🇰",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "TWD",
            symbol = "NT$",
            name = "New Taiwan Dollar",
            country = "Taiwan",
            region = "Asia",
            flag = "🇹🇼",
            decimalDigits = 0
        ),
        AppCurrency(
            code = "INR",
            symbol = "₹",
            name = "Rupee",
            country = "India",
            region = "Asia",
            flag = "🇮🇳",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "SAR",
            symbol = "SAR",
            name = "Riyal",
            country = "Arab Saudi",
            region = "Asia",
            flag = "🇸🇦",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "AED",
            symbol = "AED",
            name = "Dirham",
            country = "Uni Emirat Arab",
            region = "Asia",
            flag = "🇦🇪",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "QAR",
            symbol = "QAR",
            name = "Riyal",
            country = "Qatar",
            region = "Asia",
            flag = "🇶🇦",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "TRY",
            symbol = "₺",
            name = "Lira",
            country = "Turki",
            region = "Asia",
            flag = "🇹🇷",
            decimalDigits = 2
        ),

        // EROPA & RUSIA
        AppCurrency(
            code = "EUR",
            symbol = "€",
            name = "Euro",
            country = "Uni Eropa",
            region = "Eropa & Rusia",
            flag = "🇪🇺",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "GBP",
            symbol = "£",
            name = "Pound Sterling",
            country = "Inggris (UK)",
            region = "Eropa & Rusia",
            flag = "🇬🇧",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "RUB",
            symbol = "₽",
            name = "Ruble",
            country = "Rusia",
            region = "Eropa & Rusia",
            flag = "🇷🇺",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "CHF",
            symbol = "CHF",
            name = "Franc",
            country = "Swiss",
            region = "Eropa & Rusia",
            flag = "🇨🇭",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "PLN",
            symbol = "zł",
            name = "Zloty",
            country = "Polandia",
            region = "Eropa & Rusia",
            flag = "🇵🇱",
            decimalDigits = 2
        ),

        // AFRIKA
        AppCurrency(
            code = "ZAR",
            symbol = "R",
            name = "Rand",
            country = "Afrika Selatan",
            region = "Afrika",
            flag = "🇿🇦",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "EGP",
            symbol = "E£",
            name = "Egyptian Pound",
            country = "Mesir",
            region = "Afrika",
            flag = "🇪🇬",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "NGN",
            symbol = "₦",
            name = "Naira",
            country = "Nigeria",
            region = "Afrika",
            flag = "🇳🇬",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "KES",
            symbol = "KSh",
            name = "Shilling",
            country = "Kenya",
            region = "Afrika",
            flag = "🇰🇪",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "MAD",
            symbol = "MAD",
            name = "Dirham",
            country = "Maroko",
            region = "Afrika",
            flag = "🇲🇦",
            decimalDigits = 2
        ),
        AppCurrency(
            code = "GHS",
            symbol = "GH₵",
            name = "Cedi",
            country = "Ghana",
            region = "Afrika",
            flag = "🇬🇭",
            decimalDigits = 2
        )
    )

    fun getCurrency(code: String): AppCurrency {
        return ALL_CURRENCIES.find { it.code.equals(code, ignoreCase = true) }
            ?: ALL_CURRENCIES.first()
    }
}
