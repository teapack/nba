package cz.vratislavjindra.nba.common.design.system.compose.spacing

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
fun VerticalSpacing() {
    Spacer(modifier = Modifier.height(height = Theme.dimensions.paddingSmall))
}

@Composable
fun HorizontalSpacing() {
    Spacer(modifier = Modifier.width(width = Theme.dimensions.paddingSmall))
}
