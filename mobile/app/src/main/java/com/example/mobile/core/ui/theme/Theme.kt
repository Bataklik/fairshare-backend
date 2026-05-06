package com.example.mobile.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = AppButtonGreen,
    onPrimary = AppWhite,
    background = AppBackground,
    onBackground = AppDark,
    surface = AppWhite,
    onSurface = AppTextGrey,
    tertiary = AppTealDark

)

@Composable
fun FairShareTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}