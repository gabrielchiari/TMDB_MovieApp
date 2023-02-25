package com.gabrielchiariapps.tmdbmovieapp.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.gabrielchiariapps.tmdbmovieapp.R

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = FontFamily(Font(R.font.inter_regular)),
    h1 = TextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
    h2 = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
    h3 = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    subtitle1 = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Medium),
    subtitle2 = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium),
    body1 = TextStyle(fontSize = 16.sp),
    body2 = TextStyle(fontSize = 14.sp),
    button = TextStyle(fontSize = 14.sp, fontWeight = FontWeight.Medium, letterSpacing = 1.sp),
    caption = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal),
    overline = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Normal, letterSpacing = 1.sp)
)
