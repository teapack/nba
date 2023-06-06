package cz.vratislavjindra.nba.common.player.domain

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.player.model.PlayerList

interface PlayerRepository {

    suspend fun getPlayers(page: Int): Result<PlayerList, AppError>

    suspend fun getPlayerDetail(id: Int): Result<PlayerDetail, AppError>
}
