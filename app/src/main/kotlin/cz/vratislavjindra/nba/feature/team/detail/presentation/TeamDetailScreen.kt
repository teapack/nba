package cz.vratislavjindra.nba.feature.team.detail.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import cz.vratislavjindra.nba.R
import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.design.system.compose.error.ErrorScreen
import cz.vratislavjindra.nba.common.design.system.compose.image.TeamLogo
import cz.vratislavjindra.nba.common.design.system.compose.layout.LabelTextListItem
import cz.vratislavjindra.nba.common.design.system.compose.layout.NbaScaffold
import cz.vratislavjindra.nba.common.design.system.compose.loading.LoadingScreen
import cz.vratislavjindra.nba.common.design.system.compose.spacing.VerticalSpacing
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme
import cz.vratislavjindra.nba.common.team.model.Team

@Composable
fun TeamDetailScreen(
    viewModel: TeamDetailViewModel = hiltViewModel(),
    navigateBack: () -> Unit,
) {
    val uiState by viewModel.uiState.collectAsState()
    NbaScaffold(
        appBarTitle = uiState.team?.fullName.orEmpty(),
        upNavigationAction = navigateBack,
    ) {
        TeamDetailContent(
            state = uiState,
            paddingValues = it,
            onTryAgain = viewModel::getTeamDetail,
        )
    }
}

@Composable
private fun TeamDetailContent(
    state: TeamDetailViewModel.State,
    paddingValues: PaddingValues,
    onTryAgain: () -> Unit,
) {
    when {
        state.loading -> LoadingScreen()
        state.team != null -> TeamDetailLayout(team = state.team, paddingValues = paddingValues)
        else -> ErrorScreen(error = state.error ?: AppError.Unknown) { onTryAgain() }
    }
}

@Composable
private fun TeamDetailLayout(
    team: Team,
    paddingValues: PaddingValues,
) {
    Row(
        modifier = Modifier
            .padding(paddingValues = paddingValues)
            .padding(vertical = Theme.dimensions.paddingDefault)
            .verticalScroll(state = rememberScrollState()),
    ) {
        Column(modifier = Modifier.weight(weight = 1f)) {
            LabelTextListItem(labelResId = R.string.label_team_full_name, text = team.fullName)
            VerticalSpacing()
            LabelTextListItem(labelResId = R.string.label_team_name, text = team.name)
            VerticalSpacing()
            LabelTextListItem(
                labelResId = R.string.label_team_abbreviation,
                text = team.abbreviation
            )
            VerticalSpacing()
            LabelTextListItem(labelResId = R.string.label_team_city, text = team.city)
            VerticalSpacing()
            LabelTextListItem(labelResId = R.string.label_team_conference, text = team.conference)
            VerticalSpacing()
            LabelTextListItem(labelResId = R.string.label_team_division, text = team.division)
        }
        TeamLogo(
            modifier = Modifier.padding(end = Theme.dimensions.paddingDefault),
            logoResId = team.teamLogoResId,
            teamName = team.name,
        )
    }
}
