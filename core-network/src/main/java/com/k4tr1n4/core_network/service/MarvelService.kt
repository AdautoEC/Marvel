package com.k4tr1n4.core_network.service

import com.skydoves.sandwich.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelService {

    @GET("/v1/public/characters")
    suspend fun fetchMarvelList(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0
    ): ApiResponse<String>

    @GET("/v1/public/characters")
    suspend fun fetchMarvelInfo(
        @Path("characterId") id: String = "1"
    ): ApiResponse<String>
}