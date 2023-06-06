package cz.vratislavjindra.nba.common.player.domain

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.architecture.infrastructure.mapSuccess
import cz.vratislavjindra.nba.common.coroutines.domain.SuspendUseCase
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.player.model.PlayerList
import cz.vratislavjindra.nba.common.team.domain.TeamUseCase
import javax.inject.Inject

sealed interface PlayerUseCase {

    class GetPlayers @Inject constructor(
        private val repository: PlayerRepository,
        private val getTeamLogo: TeamUseCase.GetTeamLogo,
    ) : SuspendUseCase<Int, Result<PlayerList, AppError>>() {

        override suspend fun invoke(input: Int): Result<PlayerList, AppError> = repository
            .getPlayers(page = input)
            .mapSuccess { playerList ->
                playerList.copy(
                    players = playerList.players.map { player ->
                        player.copy(teamLogoResId = getTeamLogo(input = player.teamName))
                    },
                )
            }
    }

    class GetPlayerDetail @Inject constructor(
        private val repository: PlayerRepository,
        private val getTeamLogo: TeamUseCase.GetTeamLogo,
    ) : SuspendUseCase<Int, Result<PlayerDetail, AppError>>() {

        override suspend fun invoke(input: Int): Result<PlayerDetail, AppError> = repository
            .getPlayerDetail(id = input)
            .mapSuccess { playerDetail ->
                playerDetail.copy(
                    team = playerDetail.team.copy(
                        teamLogoResId = getTeamLogo(input = playerDetail.team.name),
                    ),
                )
            }
    }
}
