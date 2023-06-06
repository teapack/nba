package cz.vratislavjindra.nba.common.design.system.compose.appbar

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import cz.vratislavjindra.nba.R
import cz.vratislavjindra.nba.common.design.system.compose.utils.applyTonalElevation

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NbaTopAppBar(
    title: String,
    upNavigationAction: (() -> Unit)?,
    actions: List<TopAppBarAction>,
) {
    val systemUiController = rememberSystemUiController()
    val systemIsInDarkTheme = isSystemInDarkTheme()
    SideEffect {
        systemUiController.setStatusBarColor(
            color = Color.Transparent,
            darkIcons = !systemIsInDarkTheme
        )
    }
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.titleLarge
            )
        },
        modifier = Modifier
            .background(
                color = MaterialTheme.colorScheme
                    .applyTonalElevation(
                        backgroundColor = MaterialTheme.colorScheme.surface,
                        elevation = 4.dp,
                    )
                    .copy(alpha = 0.9f),
            ),
        navigationIcon = {
            if (upNavigationAction != null) {
                AppBarButton(
                    icon = Icons.Rounded.ArrowBack,
                    contentDescription = stringResource(
                        id = R.string.content_description_button_navigate_up,
                    ),
                    onClick = upNavigationAction,
                )
            }
        },
        actions = {
            actions.forEach {
                AppBarButton(
                    icon = it.icon,
                    contentDescription = it.contentDescription,
                    onClick = it.onClick,
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun AppBarButton(
    icon: ImageVector,
    contentDescription: String,
    onClick: () -> Unit,
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            tint = MaterialTheme.colorScheme.onSurface,
        )
    }
}
