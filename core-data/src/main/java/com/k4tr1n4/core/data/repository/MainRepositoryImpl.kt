package com.k4tr1n4.core.data.repository

import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.annotation.WorkerThread
import com.k4tr1n4.core.database.CharacterDao
import com.k4tr1n4.core.database.entity.mapper.asDomain
import com.k4tr1n4.core.database.entity.mapper.asEntity
import com.k4tr1n4.core.network.Dispatcher
import com.k4tr1n4.core.network.MarvelAppDispatchers
import com.k4tr1n4.core.network.service.MarvelClient
import com.skydoves.sandwich.message
import com.skydoves.sandwich.onError
import com.skydoves.sandwich.onFailure
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnFailure
import com.skydoves.sandwich.suspendOnSuccess
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@VisibleForTesting
class MainRepositoryImpl @Inject constructor(
    private val marvelClient: MarvelClient,
    private val marvelDao: CharacterDao,
    @Dispatcher(MarvelAppDispatchers.IO) private val ioDispatcher: CoroutineDispatcher
): MainRepository {

    @WorkerThread
    override fun fetchMarvelList(
        page: Int,
        onStart: () -> Unit,
        onComplete: () -> Unit,
        onError: (String?) -> Unit
    ) = flow {
        var characters = marvelDao.getCharacterList(page).asDomain()
        if(characters.isEmpty()){
            val response = marvelClient.fetchMarvelList(page = page)
            response.suspendOnSuccess {
                characters = data.data.results
                characters.forEach { character -> character.page = page}
                marvelDao.insertCharacterList(characters.asEntity())
                emit(marvelDao.getAllCharacterList(page).asDomain())
            }.onFailure {
                onError(message())
            }
        } else{
            emit(marvelDao.getAllCharacterList(page).asDomain())
        }
    }
        .onStart { onStart() }
        .onCompletion { onComplete() }
        .flowOn(ioDispatcher)
}