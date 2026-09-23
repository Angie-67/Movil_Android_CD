package com.vasquez.navlab.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = PurplePrimary,
    onPrimary = White,
    secondary = PurpleMedium,
    background = PurpleLightBackground,
    surface = PurpleCardBackground,
    onBackground = PurpleTextTitle,
    onSurface = PurpleTextTitle
)

@Composable
fun NavLabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}
