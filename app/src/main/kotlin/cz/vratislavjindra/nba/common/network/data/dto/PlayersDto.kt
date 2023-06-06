package cz.vratislavjindra.nba.common.network.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayersDto(
    @SerialName("data")
    val data: List<PlayerDto>,
    @SerialName("meta")
    val meta: MetaDto,
)
