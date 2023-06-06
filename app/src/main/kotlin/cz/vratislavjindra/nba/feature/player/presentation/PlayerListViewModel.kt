package cz.vratislavjindra.nba.feature.player.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.player.domain.PlayerUseCase
import cz.vratislavjindra.nba.common.player.model.Player
import cz.vratislavjindra.nba.common.player.model.PlayerList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerListViewModel @Inject constructor(
    private val getPlayers: PlayerUseCase.GetPlayers,
) : ViewModel() {

    private val _uiState = MutableStateFlow(value = State())
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    init {
        loadPlayers()
    }

    fun loadMorePlayers(isAtBottom: Boolean) {
        if (isAtBottom && !uiState.value.loading) {
            loadPlayers()
        }
    }

    fun reloadData() {
        _uiState.update { State() }
        loadPlayers()
    }

    private fun loadPlayers() = viewModelScope.launch {
        _uiState.update { it.copy(loading = true) }
        uiState.value.nextPlayerListPageNumber?.let {
            getPlayers(input = it).handlePlayersResult()
        }
    }

    private fun Result<PlayerList, AppError>.handlePlayersResult() = when (this) {
        is Result.Success -> _uiState.update {
            it.copy(
                players = it.players.toMutableList().apply { addAll(data.players) }.toList(),
                nextPlayerListPageNumber = data.nextPage,
                loading = false,
                error = null,
            )
        }
        is Result.Fail -> _uiState.update { it.copy(loading = false, error = error) }
    }

    data class State(
        val players: List<Player> = emptyList(),
        val nextPlayerListPageNumber: Int? = 0,
        val loading: Boolean = true,
        val error: AppError? = null,
    )
}
