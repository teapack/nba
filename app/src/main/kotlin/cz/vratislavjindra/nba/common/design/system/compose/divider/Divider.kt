package cz.vratislavjindra.nba.common.design.system.compose.divider

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
fun HorizontalDividerLine() {
    Column(modifier = Modifier.padding(vertical = Theme.dimensions.paddingDefault)) {
        Divider()
    }
}
