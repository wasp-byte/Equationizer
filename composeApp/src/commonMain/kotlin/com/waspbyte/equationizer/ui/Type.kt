package com.waspbyte.equationizer.ui

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import equationizer.composeapp.generated.resources.B612_regular
import equationizer.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

val AppTypography
@Composable
get() = Typography().let {
    val b612Font = FontFamily(Font(Res.font.B612_regular))
    it.copy(
    displayLarge = it.displayLarge.copy(fontFamily = b612Font),
    displayMedium = it.displayMedium.copy(fontFamily = b612Font),
    displaySmall = it.displaySmall.copy(fontFamily = b612Font),
    headlineLarge = it.headlineLarge.copy(fontFamily = b612Font),
    headlineMedium = it.headlineMedium.copy(fontFamily = b612Font),
    headlineSmall = it.headlineSmall.copy(fontFamily = b612Font),
    titleLarge = it.titleLarge.copy(fontFamily = b612Font),
    titleMedium = it.titleMedium.copy(fontFamily = b612Font),
    titleSmall = it.titleSmall.copy(fontFamily = b612Font),
    bodyLarge = it.bodyLarge.copy(fontFamily = b612Font),
    bodyMedium = it.bodyMedium.copy(fontFamily = b612Font),
    bodySmall = it.bodySmall.copy(fontFamily = b612Font),
    labelLarge = it.labelLarge.copy(fontFamily = b612Font),
    labelMedium = it.labelMedium.copy(fontFamily = b612Font),
    labelSmall = it.labelSmall.copy(fontFamily = b612Font),
    )
}