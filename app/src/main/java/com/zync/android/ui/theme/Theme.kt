package com.zync.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DeepBlueColorScheme = darkColorScheme(
    primary = Color(0xFF1A8FD1),
    background = Color(0xFF060B14),
    surface = Color(0xFF0D1421),
    onBackground = Color(0xFFE2E8F0),
    onSurface = Color(0xFFE2E8F0)
)

@Composable
fun ZyncTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DeepBlueColorScheme,
        content = content
    )
}
