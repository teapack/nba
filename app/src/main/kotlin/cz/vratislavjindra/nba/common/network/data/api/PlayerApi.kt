package cz.vratislavjindra.nba.common.network.data.api

import cz.vratislavjindra.nba.common.network.data.dto.PlayerDto
import cz.vratislavjindra.nba.common.network.data.dto.PlayersDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlayerApi {

    @GET("v1/players/")
    suspend fun getPlayers(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int,
    ): Response<PlayersDto>

    @GET("v1/players/{id}")
    suspend fun getPlayerDetail(@Path("id") id: Int): Response<PlayerDto>
}
