package com.k4tr1n4.core.data.repository

import com.k4tr1n4.core_network.Dispatcher
import com.k4tr1n4.core_network.MarvelAppDispatchers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    @Dispatcher(MarvelAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): MainRepository {
    override fun fetchMarvelList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        emit(arrayListOf(""))
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}