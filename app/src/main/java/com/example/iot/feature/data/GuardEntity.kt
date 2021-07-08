package com.example.iot.feature.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GuardEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "Family") val family: String,
    @ColumnInfo(name = "lastLocationLAT") val lastLocationLAT: String,
    @ColumnInfo(name = "lastLocationLANG") val lastLocationLANG: String
) {
//    fun toGuard(): ActiveGuard {
//        return ActiveGuard(
//            id, name, family, lastLocationLAT.toDouble(), lastLocationLANG.toDouble()
//        )
//    }
}

data class ActiveGuard(
    val id: Int,
    val name: String,
    @SerializedName("band__band_id") val bandId: Int?,
    @SerializedName("staff_id") val staffId: Int,
    @SerializedName("date_joined") val dateJoined: String
)