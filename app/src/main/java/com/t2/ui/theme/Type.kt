package com.t2.ui.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.t2.R

val Quicksand = FontFamily(
    Font(
        resId = R.font.quicksand_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.quicksand_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.quicksand_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.quicksand_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.quicksand_semibold,
        weight = FontWeight.SemiBold
    ),
)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
    ),
    headlineSmall = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
    ),
    titleLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.SemiBold,
        fontSize = 20.sp,
    ),
    titleMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelMedium = TextStyle(
        fontFamily = Quicksand,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
    ),
)
