package com.k4tr1n4.core_network.service

import com.skydoves.sandwich.ApiResponse
import javax.inject.Inject

class MarvelClient @Inject constructor(
    private val marvelService: MarvelService
) {
    suspend fun fetchMarvelList(
        page: Int
    ): ApiResponse<String> = marvelService.fetchMarvelList(
        limit = PAGING_SIZE,
        offset = page * PAGING_SIZE
    )

    suspend fun fetchMarvelInfo(
        id: String
    ): ApiResponse<String> = marvelService.fetchMarvelInfo(
        id = id
    )

    companion object {
        private const val PAGING_SIZE = 20
    }
}