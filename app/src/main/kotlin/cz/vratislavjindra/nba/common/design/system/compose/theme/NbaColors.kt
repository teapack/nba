package cz.vratislavjindra.nba.common.design.system.compose.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

@Immutable
data class NbaColors(
    val primary: Color = Color.Unspecified,
    val primaryVariant: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val secondaryVariant: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
    val onError: Color = Color.Unspecified,
    val success: Color = Color.Unspecified,
    val onSuccess: Color = Color.Unspecified,
    val isLight: Boolean = true,
)

internal fun initializeLightColors(): NbaColors = NbaColors(
    primary = Colors.DeepOrange500,
    primaryVariant = Colors.DeepOrange700,
    onPrimary = Colors.White,
    secondary = Colors.Indigo500,
    secondaryVariant = Colors.Indigo700,
    onSecondary = Colors.White,
    background = Colors.LightGrey100,
    onBackground = Colors.Black,
    surface = Colors.White,
    onSurface = Colors.Black,
    error = Colors.Red700,
    onError = Colors.White,
    success = Colors.Green500,
    onSuccess = Colors.White,
    isLight = true,
)

internal fun initializeDarkColors(): NbaColors = NbaColors(
    primary = Colors.DeepOrange500,
    primaryVariant = Colors.DeepOrange700,
    onPrimary = Colors.White,
    secondary = Colors.Indigo500,
    secondaryVariant = Colors.Indigo700,
    onSecondary = Colors.White,
    background = Colors.DarkGrey1,
    onBackground = Colors.White,
    surface = Colors.DarkGrey2,
    onSurface = Colors.White,
    error = Colors.Red700,
    onError = Colors.White,
    success = Colors.Green500,
    onSuccess = Colors.White,
    isLight = false,
)

internal val LocalColors = staticCompositionLocalOf { NbaColors() }
