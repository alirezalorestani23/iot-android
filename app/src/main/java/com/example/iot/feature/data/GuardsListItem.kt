package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

data class GuardsListItem(
    val id: Int,
    val name: String,
    @SerializedName("band__band_id") val bandId: Int?,
    @SerializedName("staff_id") val staffId: Int,
    @SerializedName("date_joined") val dateJoined: String?,
    @SerializedName("date_left") val dateLeft: String?
) {
    fun isActive() = bandId == null
}

