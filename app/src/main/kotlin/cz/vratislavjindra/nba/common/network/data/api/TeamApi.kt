package cz.vratislavjindra.nba.common.network.data.api

import cz.vratislavjindra.nba.common.network.data.dto.TeamDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TeamApi {

    @GET("v1/teams/{id}")
    suspend fun getTeamDetail(@Path("id") id: Int): Response<TeamDto>
}
