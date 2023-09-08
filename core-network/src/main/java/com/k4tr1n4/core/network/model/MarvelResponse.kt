package com.k4tr1n4.core.network.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarvelResponse<T>(
    @field:Json(name = "code") val code: Int?,
    @field:Json(name = "status") val status: String?,
    @field:Json(name = "copyright") val copyright: String?,
    @field:Json(name = "etag") val etag: String?,
    @field:Json(name = "data") val data: T,
)
