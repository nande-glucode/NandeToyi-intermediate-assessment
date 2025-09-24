package com.example.getitdone.presentation.screens.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.getitdone.presentation.ui.theme.backgroundDark
import com.example.getitdone.presentation.ui.theme.backgroundLight
import com.example.getitdone.presentation.ui.theme.inverseOnSurfaceDark
import com.example.getitdone.presentation.ui.theme.inverseOnSurfaceLight
import com.example.getitdone.presentation.ui.theme.inversePrimaryDark
import com.example.getitdone.presentation.ui.theme.inversePrimaryLight
import com.example.getitdone.presentation.ui.theme.inverseSurfaceDark
import com.example.getitdone.presentation.ui.theme.inverseSurfaceLight
import com.example.getitdone.presentation.ui.theme.onBackgroundDark
import com.example.getitdone.presentation.ui.theme.onBackgroundLight
import com.example.getitdone.presentation.ui.theme.onPrimaryContainerDark
import com.example.getitdone.presentation.ui.theme.onPrimaryContainerLight
import com.example.getitdone.presentation.ui.theme.onPrimaryDark
import com.example.getitdone.presentation.ui.theme.onPrimaryLight
import com.example.getitdone.presentation.ui.theme.onSecondaryContainerDark
import com.example.getitdone.presentation.ui.theme.onSecondaryContainerLight
import com.example.getitdone.presentation.ui.theme.onSecondaryDark
import com.example.getitdone.presentation.ui.theme.onSecondaryLight
import com.example.getitdone.presentation.ui.theme.onSurfaceDark
import com.example.getitdone.presentation.ui.theme.onSurfaceLight
import com.example.getitdone.presentation.ui.theme.onSurfaceVariantDark
import com.example.getitdone.presentation.ui.theme.onSurfaceVariantLight
import com.example.getitdone.presentation.ui.theme.onTertiaryContainerDark
import com.example.getitdone.presentation.ui.theme.onTertiaryContainerLight
import com.example.getitdone.presentation.ui.theme.onTertiaryDark
import com.example.getitdone.presentation.ui.theme.onTertiaryLight
import com.example.getitdone.presentation.ui.theme.outlineDark
import com.example.getitdone.presentation.ui.theme.outlineLight
import com.example.getitdone.presentation.ui.theme.outlineVariantDark
import com.example.getitdone.presentation.ui.theme.outlineVariantLight
import com.example.getitdone.presentation.ui.theme.primaryContainerDark
import com.example.getitdone.presentation.ui.theme.primaryContainerLight
import com.example.getitdone.presentation.ui.theme.primaryDark
import com.example.getitdone.presentation.ui.theme.primaryLight
import com.example.getitdone.presentation.ui.theme.scrimDark
import com.example.getitdone.presentation.ui.theme.scrimLight
import com.example.getitdone.presentation.ui.theme.secondaryContainerDark
import com.example.getitdone.presentation.ui.theme.secondaryContainerLight
import com.example.getitdone.presentation.ui.theme.secondaryDark
import com.example.getitdone.presentation.ui.theme.secondaryLight
import com.example.getitdone.presentation.ui.theme.surfaceBrightDark
import com.example.getitdone.presentation.ui.theme.surfaceBrightLight
import com.example.getitdone.presentation.ui.theme.surfaceContainerDark
import com.example.getitdone.presentation.ui.theme.surfaceContainerHighDark
import com.example.getitdone.presentation.ui.theme.surfaceContainerHighLight
import com.example.getitdone.presentation.ui.theme.surfaceContainerHighestDark
import com.example.getitdone.presentation.ui.theme.surfaceContainerHighestLight
import com.example.getitdone.presentation.ui.theme.surfaceContainerLight
import com.example.getitdone.presentation.ui.theme.surfaceContainerLowDark
import com.example.getitdone.presentation.ui.theme.surfaceContainerLowLight
import com.example.getitdone.presentation.ui.theme.surfaceContainerLowestDark
import com.example.getitdone.presentation.ui.theme.surfaceContainerLowestLight
import com.example.getitdone.presentation.ui.theme.surfaceDark
import com.example.getitdone.presentation.ui.theme.surfaceDimDark
import com.example.getitdone.presentation.ui.theme.surfaceDimLight
import com.example.getitdone.presentation.ui.theme.surfaceLight
import com.example.getitdone.presentation.ui.theme.surfaceVariantDark
import com.example.getitdone.presentation.ui.theme.surfaceVariantLight
import com.example.getitdone.presentation.ui.theme.tertiaryContainerDark
import com.example.getitdone.presentation.ui.theme.tertiaryContainerLight
import com.example.getitdone.presentation.ui.theme.tertiaryDark
import com.example.getitdone.presentation.ui.theme.tertiaryLight

private val lightScheme = lightColorScheme(
    primary = primaryLight,
    onPrimary = onPrimaryLight,
    primaryContainer = primaryContainerLight,
    onPrimaryContainer = onPrimaryContainerLight,
    secondary = secondaryLight,
    onSecondary = onSecondaryLight,
    secondaryContainer = secondaryContainerLight,
    onSecondaryContainer = onSecondaryContainerLight,
    tertiary = tertiaryLight,
    onTertiary = onTertiaryLight,
    tertiaryContainer = tertiaryContainerLight,
    onTertiaryContainer = onTertiaryContainerLight,
    background = backgroundLight,
    onBackground = onBackgroundLight,
    surface = surfaceLight,
    onSurface = onSurfaceLight,
    surfaceVariant = surfaceVariantLight,
    onSurfaceVariant = onSurfaceVariantLight,
    outline = outlineLight,
    outlineVariant = outlineVariantLight,
    scrim = scrimLight,
    inverseSurface = inverseSurfaceLight,
    inverseOnSurface = inverseOnSurfaceLight,
    inversePrimary = inversePrimaryLight,
    surfaceDim = surfaceDimLight,
    surfaceBright = surfaceBrightLight,
    surfaceContainerLowest = surfaceContainerLowestLight,
    surfaceContainerLow = surfaceContainerLowLight,
    surfaceContainer = surfaceContainerLight,
    surfaceContainerHigh = surfaceContainerHighLight,
    surfaceContainerHighest = surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = primaryDark,
    onPrimary = onPrimaryDark,
    primaryContainer = primaryContainerDark,
    onPrimaryContainer = onPrimaryContainerDark,
    secondary = secondaryDark,
    onSecondary = onSecondaryDark,
    secondaryContainer = secondaryContainerDark,
    onSecondaryContainer = onSecondaryContainerDark,
    tertiary = tertiaryDark,
    onTertiary = onTertiaryDark,
    tertiaryContainer = tertiaryContainerDark,
    onTertiaryContainer = onTertiaryContainerDark,
    background = backgroundDark,
    onBackground = onBackgroundDark,
    surface = surfaceDark,
    onSurface = onSurfaceDark,
    surfaceVariant = surfaceVariantDark,
    onSurfaceVariant = onSurfaceVariantDark,
    outline = outlineDark,
    outlineVariant = outlineVariantDark,
    scrim = scrimDark,
    inverseSurface = inverseSurfaceDark,
    inverseOnSurface = inverseOnSurfaceDark,
    inversePrimary = inversePrimaryDark,
    surfaceDim = surfaceDimDark,
    surfaceBright = surfaceBrightDark,
    surfaceContainerLowest = surfaceContainerLowestDark,
    surfaceContainerLow = surfaceContainerLowDark,
    surfaceContainer = surfaceContainerDark,
    surfaceContainerHigh = surfaceContainerHighDark,
    surfaceContainerHighest = surfaceContainerHighestDark,
)


@Immutable
data class ColorFamily(
    val color: Color,
    val onColor: Color,
    val colorContainer: Color,
    val onColorContainer: Color
)

val unspecified_scheme = ColorFamily(
    Color.Unspecified, Color.Unspecified, Color.Unspecified, Color.Unspecified
)

@Composable
fun GetItDoneTheme(
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

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}