package cz.vratislavjindra.nba.common.design.system.compose.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class NbaShapes(
    val roundedCornerSmall: Shape = RoundedCornerShape(8.dp),
    val roundedCornerDefault: Shape = RoundedCornerShape(size = 16.dp),
    val roundedCornerLarge: Shape = RoundedCornerShape(24.dp),
)

internal val LocalShapes = staticCompositionLocalOf { NbaShapes() }
