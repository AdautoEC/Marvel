package com.k4tr1n4.core.network.service

import com.k4tr1n4.core.network.model.MarvelResponse
import com.k4tr1n4.core.model.FetchAllResponse
import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MarvelClient @Inject constructor(
    private val marvelService: MarvelService
) {
    suspend fun fetchMarvelList(
        page: Int
    ): ApiResponse<MarvelResponse<FetchAllResponse>> = marvelService.fetchMarvelList(
        limit = PAGING_SIZE,
        offset = page * PAGING_SIZE
    )

    suspend fun fetchMarvelInfo(
        id: String
    ): ApiResponse<FetchAllResponse> = marvelService.fetchMarvelInfo(
        id = id
    )

    companion object {
        private const val PAGING_SIZE = 20
    }
}