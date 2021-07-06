package com.example.iot.feature.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GuardEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name="name")val name: String,
    @ColumnInfo(name="Family")val family: String,
    @ColumnInfo(name="lastLocationLAT")val lastLocationLAT: String,
    @ColumnInfo(name="lastLocationLANG")val lastLocationLANG: String
){
    fun toGuard():Guard{
       return Guard(
            id,name,family,lastLocationLAT.toDouble(),lastLocationLANG.toDouble()
        )
    }
}

data class Guard(
    val id: Int,
    val name: String,
    val Family: String,
    val lastLocationLAT: Double,
    val lastLocationLANG: Double
)