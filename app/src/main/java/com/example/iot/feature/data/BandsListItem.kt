package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

data class BandsListItem(
    val id: Int,
    @SerializedName("band_id") val bandId: Int,
    @SerializedName("guard__staff_id") val staffId: Int?,
    @SerializedName("is_deleted") val isDeleted: Boolean = false
)
