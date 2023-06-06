package cz.vratislavjindra.nba.common.design.system.compose.text

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
fun SectionTitle(text: String) {
    Text(
        text = text,
        modifier = Modifier.padding(all = Theme.dimensions.paddingDefault),
        style = MaterialTheme.typography.headlineSmall,
    )
}
