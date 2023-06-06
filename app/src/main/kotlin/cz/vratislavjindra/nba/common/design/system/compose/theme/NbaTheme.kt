package cz.vratislavjindra.nba.common.design.system.compose.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.platform.LocalContext

object Theme {
    val colors: NbaColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current
    val dimensions: NbaDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalDimensions.current
    val shapes: NbaShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalShapes.current
}

private val DarkColorScheme = darkColorScheme(
    primary = Colors.DeepOrange500,
    secondary = Colors.Indigo500,
    tertiary = Colors.DeepOrange700,
    background = Colors.DarkGrey1,
    surface = Colors.DarkGrey2,
    onPrimary = Colors.White,
    onSecondary = Colors.White,
    onTertiary = Colors.White,
    onBackground = Colors.White,
    onSurface = Colors.White,
    error = Colors.Red700,
    onError = Colors.White,
)

private val LightColorScheme = lightColorScheme(
    primary = Colors.DeepOrange500,
    secondary = Colors.Indigo500,
    tertiary = Colors.DeepOrange700,
    background = Colors.LightGrey100,
    surface = Colors.White,
    onPrimary = Colors.White,
    onSecondary = Colors.White,
    onTertiary = Colors.White,
    onBackground = Colors.Black,
    onSurface = Colors.Black,
    error = Colors.Red700,
    onError = Colors.White,
)


@Composable
fun NbaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context = context)
            } else {
                dynamicLightColorScheme(context = context)
            }
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }
    MaterialTheme(colorScheme = colorScheme, content = content)
}