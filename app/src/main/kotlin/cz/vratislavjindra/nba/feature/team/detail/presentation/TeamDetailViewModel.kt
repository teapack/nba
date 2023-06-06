package cz.vratislavjindra.nba.feature.team.detail.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.navigation.data.NbaDestinations
import cz.vratislavjindra.nba.common.team.domain.TeamUseCase
import cz.vratislavjindra.nba.common.team.model.Team
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getTeamDetailUseCase: TeamUseCase.GetTeamDetail,
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = State())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    init {
        getTeamDetail()
    }

    fun getTeamDetail() = viewModelScope.launch {
        _uiState.update {
            State(
                teamId = savedStateHandle.get<Int>(
                    key = NbaDestinations.WithArgument.TeamDetail.navArgumentName
                ),
            )
        }
        uiState.value.teamId.let { teamId ->
            if (teamId != null) {
                getTeamDetailUseCase(input = teamId).handleTeamDetailResult()
            } else {
                _uiState.update { it.copy(loading = false, error = AppError.NullId) }
            }
        }
    }

    private fun Result<Team, AppError>.handleTeamDetailResult() = when (this) {
        is Result.Success -> _uiState.update {
            it.copy(
                team = data,
                loading = false,
                error = null,
            )
        }
        is Result.Fail -> _uiState.update { it.copy(loading = false, error = error) }
    }

    data class State(
        val loading: Boolean = true,
        val teamId: Int? = null,
        val team: Team? = null,
        val error: AppError? = null,
    )
}
