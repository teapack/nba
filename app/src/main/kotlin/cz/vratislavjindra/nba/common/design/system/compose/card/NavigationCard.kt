package cz.vratislavjindra.nba.common.design.system.compose.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NavigationCard(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    contentDescription: String,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        onClick = onClick,
        modifier = modifier.fillMaxWidth(),
        shape = Theme.shapes.roundedCornerDefault,
    ) {
        Row(
            modifier = Modifier.padding(vertical = Theme.dimensions.paddingDefault),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(weight = 1f)) {
                content()
            }
            Icon(
                modifier = Modifier.padding(end = Theme.dimensions.paddingDefault),
                imageVector = Icons.Rounded.KeyboardArrowRight,
                contentDescription = contentDescription,
            )
        }
    }
}
