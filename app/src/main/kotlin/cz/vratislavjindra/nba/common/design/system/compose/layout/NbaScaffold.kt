package cz.vratislavjindra.nba.common.design.system.compose.layout

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import cz.vratislavjindra.nba.common.design.system.compose.appbar.NbaTopAppBar
import cz.vratislavjindra.nba.common.design.system.compose.appbar.TopAppBarAction

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun NbaScaffold(
    appBarTitle: String,
    upNavigationAction: (() -> Unit)? = null,
    topAppBarActions: List<TopAppBarAction> = emptyList(),
    content: @Composable (paddingValues: PaddingValues) -> Unit,
) {
    Scaffold(
        topBar = {
            NbaTopAppBar(
                title = appBarTitle,
                upNavigationAction = upNavigationAction,
                actions = topAppBarActions,
            )
        },
    ) { content(it) }
}
