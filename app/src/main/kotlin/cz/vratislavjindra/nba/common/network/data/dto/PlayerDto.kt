package cz.vratislavjindra.nba.common.network.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PlayerDto(
    @SerialName("id")
    val id: Int,
    @SerialName("first_name")
    val firstName: String,
    @SerialName("last_name")
    val lastName: String,
    @SerialName("position")
    val position: String,
    @SerialName("height_feet")
    val heightFeet: Int? = null,
    @SerialName("height_inches")
    val heightInches: Int? = null,
    @SerialName("weight_pounds")
    val weightPounds: Int? = null,
    @SerialName("team")
    val team: TeamDto,
)
