package cz.vratislavjindra.nba.feature.player.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.navigation.data.NbaDestinations
import cz.vratislavjindra.nba.common.player.domain.PlayerUseCase
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getPlayerDetailUseCase: PlayerUseCase.GetPlayerDetail,
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = State())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    init {
        getPlayerDetail()
    }

    fun getPlayerDetail() = viewModelScope.launch {
        _uiState.update {
            State(
                playerId = savedStateHandle.get<Int>(
                    key = NbaDestinations.WithArgument.PlayerDetail.navArgumentName
                ),
            )
        }
        uiState.value.playerId.let { playerId ->
            if (playerId != null) {
                getPlayerDetailUseCase(input = playerId).handlePlayerDetailResult()
            } else {
                _uiState.update { it.copy(loading = false, error = AppError.NullId) }
            }
        }
    }

    private fun Result<PlayerDetail, AppError>.handlePlayerDetailResult() = when (this) {
        is Result.Success -> _uiState.update {
            it.copy(
                player = data,
                loading = false,
                error = null,
            )
        }
        is Result.Fail -> _uiState.update { it.copy(loading = false, error = error) }
    }

    data class State(
        val loading: Boolean = true,
        val playerId: Int? = null,
        val player: PlayerDetail? = null,
        val error: AppError? = null,
    )
}
