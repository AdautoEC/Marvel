package com.k4tr1n4.core.data.repository

import android.util.Log
import com.k4tr1n4.core.network.Dispatcher
import com.k4tr1n4.core.network.MarvelAppDispatchers
import com.k4tr1n4.core.network.service.MarvelClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val marvelClient: MarvelClient,
    @Dispatcher(MarvelAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): MainRepository {
    override fun fetchMarvelList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        val response = marvelClient.fetchMarvelList(page = page)
        response.suspendOnSuccess {
            val characters = data.data.results
            characters.forEach { character -> character.page = page}
            emit(characters)
        }.onFailure {
            onError(message())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}