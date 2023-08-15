package com.k4tr1n4.core.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FetchAllResponse(
    @field:Json(name = "offset") val offset: Int?,
    @field:Json(name = "limit") val limit: Int?,
    @field:Json(name = "total") val total: Int?,
    @field:Json(name = "count") val count: Int?,
    @field:Json(name = "results") val results: List<Character>?
)