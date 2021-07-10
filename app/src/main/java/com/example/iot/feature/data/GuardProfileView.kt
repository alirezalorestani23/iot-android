package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

data class GuardProfileView(
    val id: Int,
    val name: String,
    @SerializedName("band__band_id") val bandId: Int?,
    @SerializedName("staff_id") val staffId: Int,
    @SerializedName("date_joined") val dateJoined: String?,
    val dateLeft: String?,
    val last: GuardLastHistoryView?
) {
    fun isActive() = bandId != null
}
