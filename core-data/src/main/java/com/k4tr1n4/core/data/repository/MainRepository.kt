package com.k4tr1n4.core.data.repository

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    @WorkerThread
    fun fetchMarvelList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ): Flow<List<String>>
}