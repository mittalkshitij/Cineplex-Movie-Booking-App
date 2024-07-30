package com.example.traningcomposeapp.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.sp
import com.example.traningcomposeapp.R


private val googleFontProvider: GoogleFont.Provider by lazy {
    GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )
}

val fontFamily = FontFamily(
    Font(
        googleFont = GoogleFont("Merriweather"),
        fontProvider = googleFontProvider
    )
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

/*** TEXT STYLE REGULAR ***/

val TextStyleNormal by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal)
}

val TextStyleNormal8 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 8.sp)
}

val TextStyleNormal10 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 10.sp)
}

val TextStyleNormal12 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 12.sp)
}

val TextStyleNormal14 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 14.sp)
}

val TextStyleNormal16 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 16.sp)
}

val TextStyleNormal18 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 18.sp)
}

val TextStyleNormal20 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 20.sp)
}

val TextStyleNormal22 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 22.sp)
}

val TextStyleNormal24 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 24.sp)
}

val TextStyleNormal26 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Normal, fontSize = 26.sp)
}


/*** TEXT STYLE BOLD ***/

val TextStyleBold by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold)
}

val TextStyleBold8 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 8.sp)
}

val TextStyleBold10 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 10.sp)
}

val TextStyleBold12 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 12.sp)
}

val TextStyleBold14 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 14.sp)
}

val TextStyleBold16 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 16.sp)
}

val TextStyleBold18 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 18.sp)
}

val TextStyleBold20 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 20.sp)
}

val TextStyleBold22 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 22.sp)
}

val TextStyleBold24 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 24.sp)
}

val TextStyleBold26 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Bold, fontSize = 26.sp)
}

/*** TEXT STYLE BLACK ***/

val TextStyleBlack26 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Black, fontSize = 26.sp)
}

/*** TEXT STYLE MEDIUM ***/

val TextStyleMedium18 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Medium, fontSize = 18.sp)
}

/*** TEXT STYLE LIGHT ***/

val TextStyleLight8 by lazy {
    TextStyle(fontFamily = fontFamily, fontWeight = FontWeight.Light, fontSize = 8.sp)
}