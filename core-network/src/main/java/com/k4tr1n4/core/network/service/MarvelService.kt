package com.k4tr1n4.core.network.service

import com.k4tr1n4.core.network.model.MarvelResponse
import com.k4tr1n4.core.model.FetchAllResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun fetchMarvelList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<MarvelResponse<FetchAllResponse>>

    @GET("/v1/public/characters")
    suspend fun fetchMarvelInfo(
        @Path("characterId") id: String = "1"
    ): ApiResponse<FetchAllResponse>
}