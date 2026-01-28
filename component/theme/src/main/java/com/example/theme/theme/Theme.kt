package com.example.theme.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    background = Normal,
    onBackground = White,

    primary = BrandBG,
    onPrimary = White,

    secondary = DarkBGSecondary,
    onSecondary = DarkTextSecondary,

    tertiary = DarkBGTertiary,
    onTertiary = DarkTextTertiary,

    surface = Medium,
    onSurface = Light,

    outline = DarkBorder,
    error = Error,
)

private val LightColorScheme = lightColorScheme(
    background = BGPrimary,
    onBackground = Normal,

    primary = BrandBG,
    onPrimary = White,

    secondary = BGSecondary,
    onSecondary = TextSecondary,

    tertiary = BGTertiary,
    onTertiary = TextTertiary,

    surface = Light,
    onSurface = Medium,

    outline = Border,
    error = Error,
)

@Composable
fun Pizzamityushinshift2026Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}