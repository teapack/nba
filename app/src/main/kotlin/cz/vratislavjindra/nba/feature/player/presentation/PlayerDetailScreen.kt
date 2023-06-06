package cz.vratislavjindra.nba.feature.player.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import cz.vratislavjindra.nba.R
import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.design.system.compose.card.NavigationCard
import cz.vratislavjindra.nba.common.design.system.compose.divider.HorizontalDividerLine
import cz.vratislavjindra.nba.common.design.system.compose.error.ErrorScreen
import cz.vratislavjindra.nba.common.design.system.compose.image.TeamLogo
import cz.vratislavjindra.nba.common.design.system.compose.layout.LabelTextListItem
import cz.vratislavjindra.nba.common.design.system.compose.layout.NbaScaffold
import cz.vratislavjindra.nba.common.design.system.compose.loading.LoadingScreen
import cz.vratislavjindra.nba.common.design.system.compose.spacing.VerticalSpacing
import cz.vratislavjindra.nba.common.design.system.compose.text.SectionTitle
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.team.model.Team

@Composable
fun PlayerDetailScreen(
    viewModel: PlayerDetailViewModel = hiltViewModel(),
    navigateToTeamDetail: (Int) -> Unit,
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    NbaScaffold(
        appBarTitle = uiState.player?.fullName().orEmpty(),
        upNavigationAction = navigateBack,
    ) {
        PlayerDetailContent(
            state = uiState,
            paddingValues = it,
            navigateToTeamDetail = navigateToTeamDetail,
            onTryAgain = viewModel::getPlayerDetail,
        )
    }
}

@Composable
private fun PlayerDetailContent(
    state: PlayerDetailViewModel.State,
    paddingValues: PaddingValues,
    navigateToTeamDetail: (Int) -> Unit,
    onTryAgain: () -> Unit,
) {
        when {
            state.loading -> LoadingScreen()
            state.player != null -> PlayerDetailLayout(
                player = state.player,
                paddingValues = paddingValues,
                onTeamClick = { navigateToTeamDetail(state.player.team.id) },
            )
            else -> ErrorScreen(error = state.error ?: AppError.Unknown) { onTryAgain() }
    }
}

@Composable
private fun PlayerDetailLayout(
    player: PlayerDetail,
    paddingValues: PaddingValues,
    onTeamClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(vertical = Theme.dimensions.paddingDefault)
            .verticalScroll(state = rememberScrollState()),
    ) {
        LabelTextListItem(labelResId = R.string.label_player_first_name, text = player.firstName)
        VerticalSpacing()
        LabelTextListItem(labelResId = R.string.label_player_last_name, text = player.lastName)
        VerticalSpacing()
        if (player.position.isNotEmpty()) {
            LabelTextListItem(labelResId = R.string.label_player_position, text = player.position)
            VerticalSpacing()
        }
        player.heightFeet?.let {
            LabelTextListItem(labelResId = R.string.label_player_height_feet, text = it.toString())
            VerticalSpacing()
        }
        player.heightInches?.let {
            LabelTextListItem(
                labelResId = R.string.label_player_height_inches,
                text = it.toString(),
            )
            VerticalSpacing()
        }
        player.weightPounds?.let {
            LabelTextListItem(
                labelResId = R.string.label_player_weight_pounds,
                text = it.toString(),
            )
            VerticalSpacing()
        }
        HorizontalDividerLine()
        SectionTitle(text = stringResource(id = R.string.title_team_details))
        TeamCard(team = player.team, onTeamClick = onTeamClick)
    }
}

@Composable
private fun TeamCard(
    team: Team,
    onTeamClick: () -> Unit,
) {
    NavigationCard(
        modifier = Modifier.padding(horizontal = Theme.dimensions.paddingDefault),
        onClick = onTeamClick,
        contentDescription = stringResource(id = R.string.content_description_open_team_detail),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TeamLogo(logoResId = team.teamLogoResId, teamName = team.name)
            Column(modifier = Modifier.weight(weight = 1f)) {
                LabelTextListItem(labelResId = R.string.label_team_name, text = team.name)
                VerticalSpacing()
                LabelTextListItem(labelResId = R.string.label_team_city, text = team.city)
                VerticalSpacing()
                LabelTextListItem(labelResId = R.string.label_team_conference, text = team.conference)
            }
        }
    }
}
