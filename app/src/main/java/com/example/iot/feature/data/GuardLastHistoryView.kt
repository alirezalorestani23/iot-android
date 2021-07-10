package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

data class GuardLastHistoryView(
    val id: Int,
    val lat: Double,
    val lang: Double,
    @SerializedName("wristband_id") val wristbandId: Int,
    @SerializedName("guard_id") val guardId: Int,
    val time: String,
    @SerializedName("heartbeat") val heartBeat: Int?,
    @SerializedName("emergency_alert")val emergencyAlert: Boolean?=false
)
