package com.example.iot.feature.data

import com.google.gson.annotations.SerializedName

data class ActiveGuardsResponse (
    @SerializedName("guards") val activeGuardsList : List<ActiveGuard>
)
