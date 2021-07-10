package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

class EditBandGuardBody(
    @SerializedName("band_id") val bandId: Int,
    @SerializedName("staff_id") val staffId: Int
)
