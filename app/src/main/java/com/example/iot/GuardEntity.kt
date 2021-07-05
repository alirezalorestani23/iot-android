package com.example.iot

data class GuardEntity(
    val id: Int,
    val name: String,
    val Family: String,
    val lastLocationLAT: Float,
    val lastLocationLANG: Float
)