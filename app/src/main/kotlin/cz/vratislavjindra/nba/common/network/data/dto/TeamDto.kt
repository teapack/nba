package cz.vratislavjindra.nba.common.network.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TeamDto(
    @SerialName("id")
    val id: Int,
    @SerialName("abbreviation")
    val abbreviation: String,
    @SerialName("city")
    val city: String,
    @SerialName("conference")
    val conference: String,
    @SerialName("division")
    val division: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("name")
    val name: String,
)
