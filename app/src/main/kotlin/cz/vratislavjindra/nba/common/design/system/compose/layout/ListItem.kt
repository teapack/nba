package cz.vratislavjindra.nba.common.design.system.compose.layout

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
fun LabelTextListItem(
    @StringRes labelResId: Int,
    text: String,
) {
    ListItem(labelResId = labelResId, text = text)
}

@Composable
fun TextListItem(text: String) {
    ListItem(labelResId = null, text = text, emphasizeText = true)
}

@Composable
private fun ListItem(
    @StringRes labelResId: Int?,
    text: String,
    emphasizeText: Boolean = false,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = Theme.dimensions.paddingDefault),
    ) {
        labelResId?.let {
            Text(
                text = stringResource(id = labelResId),
                style = MaterialTheme.typography.labelSmall,
            )
        }
        Text(
            text = text,
            style = if (emphasizeText) {
                MaterialTheme.typography.bodyLarge
            } else {
                MaterialTheme.typography.bodyMedium
            },
            fontWeight = if (emphasizeText) {
                FontWeight.Bold
            } else {
                FontWeight.Normal
            },
        )
    }
}
