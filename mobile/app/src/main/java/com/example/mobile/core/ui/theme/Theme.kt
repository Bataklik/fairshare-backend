package com.example.mobile.core.ui.theme

import androidx.compose.material3.lightColorScheme

class Theme {
    private val LightColorScheme = lightColorScheme(
        primary = AppButtonTeal,
        onPrimary = AppWhite,
        background = AppBackground,
        onBackground = AppDark,
        surface = AppWhite,
        onSurface = AppTextSecondary
    )
}