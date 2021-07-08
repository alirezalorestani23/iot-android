package com.example.iot.core

data class BaseResponse<T>(val meta: Meta, val data: T)
data class Meta(val statusCode: Int, val message: String)
