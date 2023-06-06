package cz.vratislavjindra.nba.common.player.data

import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.architecture.infrastructure.Result
import cz.vratislavjindra.nba.common.network.data.api.PlayerApi
import cz.vratislavjindra.nba.common.network.data.handleResponse
import cz.vratislavjindra.nba.common.player.model.PlayerDetail
import cz.vratislavjindra.nba.common.player.model.PlayerList
import javax.inject.Inject

class PlayerRemoteDataSource @Inject constructor(
    private val playerListMapper: PlayerListMapper,
    private val playerDetailMapper: PlayerDetailMapper,
    private val api: PlayerApi,
) {

    suspend fun getPlayers(page: Int, perPage: Int): Result<PlayerList, AppError> = api
        .getPlayers(page = page, perPage = perPage)
        .handleResponse { playerListMapper.toDomain(dto = it) }

    suspend fun getPlayerDetail(id: Int): Result<PlayerDetail, AppError> = api
        .getPlayerDetail(id = id)
        .handleResponse { playerDetailMapper.toDomain(dto = it) }
}
