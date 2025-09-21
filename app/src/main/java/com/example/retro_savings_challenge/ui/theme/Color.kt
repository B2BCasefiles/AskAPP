package com.example.retro_savings_challenge.ui.theme

import androidx.compose.ui.graphics.Color

// Retro Palette from the design brief
val BaseDark = Color(0xFF1A1A1D)
val NeonCyan = Color(0xFF00E5FF)
val NeonMagenta = Color(0xFFFF66B3)
val RetroYellow = Color(0xFFFFD166)

// Material 3 Theme Color Roles for the Dark Theme
val primaryDark = NeonCyan
val onPrimaryDark = BaseDark
val primaryContainerDark = NeonCyan.copy(alpha = 0.2f)
val onPrimaryContainerDark = Color.White

val secondaryDark = NeonMagenta
val onSecondaryDark = BaseDark
val secondaryContainerDark = NeonMagenta.copy(alpha = 0.2f)
val onSecondaryContainerDark = Color.White

val tertiaryDark = RetroYellow
val onTertiaryDark = BaseDark
val tertiaryContainerDark = RetroYellow.copy(alpha = 0.2f)
val onTertiaryContainerDark = Color.White

val errorDark = Color(0xFFFF8A80)
val onErrorDark = BaseDark
val errorContainerDark = Color(0xFFFF8A80).copy(alpha = 0.3f)
val onErrorContainerDark = Color.White

val backgroundDark = BaseDark
val onBackgroundDark = Color.White

val surfaceDark = Color(0xFF212124) // A slightly lighter surface than pure black
val onSurfaceDark = Color.White

val surfaceVariantDark = Color(0xFF2B2B2B)
val onSurfaceVariantDark = Color(0xFF9E9E9E) // Gray for less important text/icons

val outlineDark = NeonCyan.copy(alpha = 0.4f)
