package com.k4tr1n4.core_network.model.mapper

import com.k4tr1n4.core_network.model.MarvelErrorResponse
import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import com.skydoves.sandwich.message

object ErrorResponseMapper: ApiErrorModelMapper<MarvelErrorResponse> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): MarvelErrorResponse {
        return MarvelErrorResponse(apiErrorResponse.statusCode.code, apiErrorResponse.message())
    }
}