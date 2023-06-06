package cz.vratislavjindra.nba.feature.player.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import cz.vratislavjindra.nba.R
import cz.vratislavjindra.nba.common.design.system.compose.card.NavigationCard
import cz.vratislavjindra.nba.common.design.system.compose.error.ErrorScreen
import cz.vratislavjindra.nba.common.design.system.compose.image.TeamLogo
import cz.vratislavjindra.nba.common.design.system.compose.layout.LabelTextListItem
import cz.vratislavjindra.nba.common.design.system.compose.layout.NbaList
import cz.vratislavjindra.nba.common.design.system.compose.layout.NbaScaffold
import cz.vratislavjindra.nba.common.design.system.compose.layout.TextListItem
import cz.vratislavjindra.nba.common.design.system.compose.loading.LoadingMore
import cz.vratislavjindra.nba.common.design.system.compose.loading.LoadingScreen
import cz.vratislavjindra.nba.common.design.system.compose.spacing.VerticalSpacing
import cz.vratislavjindra.nba.common.player.model.Player

@Composable
fun PlayerListScreen(
    viewModel: PlayerListViewModel = hiltViewModel(),
    navigateToPlayerDetail: (Int) -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    val playerListState = rememberLazyListState()
    val isAtBottom = !playerListState.canScrollForward
    LaunchedEffect(key1 = isAtBottom) {
        viewModel.loadMorePlayers(isAtBottom = isAtBottom)
    }
    NbaScaffold(appBarTitle = stringResource(id = R.string.title_player_list)) {
        PlayerListContent(
            state = uiState,
            paddingValues = it,
            playerListState = playerListState,
            navigateToPlayerDetail = navigateToPlayerDetail,
            onTryAgain = viewModel::reloadData,
        )
    }
}

@Composable
private fun PlayerListContent(
    state: PlayerListViewModel.State,
    paddingValues: PaddingValues,
    playerListState: LazyListState,
    navigateToPlayerDetail: (Int) -> Unit,
    onTryAgain: () -> Unit,
) {
    when {
        state.loading && state.players.isEmpty() -> LoadingScreen()
        state.error != null -> ErrorScreen(error = state.error) { onTryAgain() }
        else -> NbaList(state = playerListState, paddingValues = paddingValues) {
            items(items = state.players) {
                PlayerCard(player = it, onClick = { navigateToPlayerDetail(it.id) })
            }
            if (state.nextPlayerListPageNumber != null) {
                item { LoadingMore() }
            }
        }
    }
}

@Composable
private fun PlayerCard(
    player: Player,
    onClick: () -> Unit,
) {
    NavigationCard(
        onClick = onClick,
        contentDescription = stringResource(id = R.string.content_description_open_player_detail),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TeamLogo(logoResId = player.teamLogoResId, teamName = player.teamName)
            Column(modifier = Modifier.weight(weight = 1f)) {
                TextListItem(text = player.fullName())
                VerticalSpacing()
                if (player.position.isNotEmpty()) {
                    LabelTextListItem(labelResId = R.string.label_player_position, text = player.position)
                    VerticalSpacing()
                }
                LabelTextListItem(labelResId = R.string.label_player_team, text = player.teamName)
            }
        }
    }
}
