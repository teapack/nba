package cz.vratislavjindra.nba.common.design.system.compose.image

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
@OptIn(ExperimentalGlideComposeApi::class)
fun TeamLogo(
    modifier: Modifier = Modifier,
    @DrawableRes logoResId: Int?,
    teamName: String,
) {
    logoResId?.let {
        GlideImage(
            model = logoResId,
            contentDescription = teamName,
            modifier = modifier
                .padding(start = Theme.dimensions.paddingDefault)
                .size(size = 64.dp),
        )
    }
}
