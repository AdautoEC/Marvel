package com.k4tr1n4.core.data

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.verify
import com.k4tr1n4.core.data.repository.MainRepositoryImpl
import com.k4tr1n4.core.database.CharacterDao
import com.k4tr1n4.core.database.entity.mapper.asEntity
import com.k4tr1n4.core.model.Character
import com.k4tr1n4.core.network.service.MarvelClient
import com.k4tr1n4.core.network.service.MarvelService
import com.k4tr1n4.core.network.model.MarvelResponse
import com.k4tr1n4.core.test.MainCourotinesRule
import com.k4tr1n4.core.test.MockUtil.mockFetchAllResponse
import com.k4tr1n4.core.test.MockUtil.mockCharacterList
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.nhaarman.mockitokotlin2.whenever
import com.skydoves.sandwich.ApiResponse
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainRepositoryImplTest {

    private lateinit var repository: MainRepositoryImpl
    private lateinit var client: MarvelClient

    private val service: MarvelService = mock()
    private val characterDao: CharacterDao = mock()

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        client = MarvelClient(service)
        repository = MainRepositoryImpl(client, characterDao , coroutinesRule.testDispatcher)
    }

    @Test//(expected = IOException::class)
    fun fetchMarvelListFromNetworkTest() = runTest {
        val mockData = MarvelResponse(
            code = 200,
            status = "Ok",
            copyright = "© 2023 MARVEL",
            etag = "865fbc1f9988b6d3d748d53d6ccb37766b7c0b72",
            data = mockFetchAllResponse()
        )
        whenever(characterDao.getCharacterList(page_ = 0))
            .thenReturn(emptyList())
        whenever(characterDao.getAllCharacterList(page_= 0))
            .thenReturn(mockData.data.results.asEntity())
        whenever(service.fetchMarvelList())
            .thenReturn(ApiResponse.of { Response.success(mockData) })

        repository.fetchMarvelList(
            page = 0,
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem()[0]
            assertEquals(exceptItem.id, 1011334)
            assertEquals(exceptItem.name, "3-D Man")
            assertEquals(exceptItem.description, "")
            assertEquals(exceptItem, mockCharacterList()[0])
            awaitComplete()
        }

        verify(characterDao, atLeastOnce()).getCharacterList(page_ = 0)
        verify(service, atLeastOnce()).fetchMarvelList()
        verify(characterDao, atLeastOnce()).insertCharacterList(mockData.data.results.asEntity())
        verifyNoMoreInteractions(service)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun fetchMarvelListFromNetworkTest2() = runTest {
        val mockData = MarvelResponse(
            code = 200,
            status = "Ok",
            copyright = "© 2023 MARVEL",
            etag = "865fbc1f9988b6d3d748d53d6ccb37766b7c0b72",
            data = mockFetchAllResponse()
        )
        whenever(characterDao.getCharacterList(page_ = 0))
            .thenReturn(emptyList())
        whenever(characterDao.getAllCharacterList(page_= 0))
            .thenReturn(mockData.data.results.asEntity())
        whenever(service.fetchMarvelList())
            .thenReturn(ApiResponse.of { Response.success(mockData) })

        val values = mutableListOf<List<Character>>()
        backgroundScope.launch(UnconfinedTestDispatcher(testScheduler)) {
            repository.fetchMarvelList(
                page = 0,
                onStart = {},
                onComplete = {},
                onError = {}
            ).take(1).collect{
                values.add(it)
            }
        }
        //service.fetchMarvelList()
        print(values.toString())
        val exceptItem = values[0][0]
        assertEquals(exceptItem.id, 1011334)
        assertEquals(exceptItem.name, "3-D Man")
        assertEquals(exceptItem.description, "")
        assertEquals(exceptItem, mockCharacterList()[0])

        verify(characterDao, atLeastOnce()).getCharacterList(page_ = 0)
        verify(service, atLeastOnce()).fetchMarvelList()
        verify(characterDao, atLeastOnce()).insertCharacterList(mockData.data.results.asEntity())
        verifyNoMoreInteractions(service)
    }

    @Test
    fun fetchMarvelListFromDatabaseTest() = runTest {
        val mockData = MarvelResponse(
            code = 200,
            status = "Ok",
            copyright = "© 2023 MARVEL",
            etag = "865fbc1f9988b6d3d748d53d6ccb37766b7c0b72",
            data = mockFetchAllResponse()
        )
        whenever(characterDao.getCharacterList(page_ = 0))
            .thenReturn(mockData.data.results.asEntity())
        whenever(characterDao.getAllCharacterList(page_= 0))
            .thenReturn(mockData.data.results.asEntity())

        repository.fetchMarvelList(
            page = 0,
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem()[0]
            assertEquals(exceptItem.id, 1011334)
            assertEquals(exceptItem.name, "3-D Man")
            assertEquals(exceptItem.description, "")
            assertEquals(exceptItem, mockCharacterList()[0])
            awaitComplete()
        }

        verify(characterDao, atLeastOnce()).getCharacterList(page_ = 0)
        verify(characterDao, atLeastOnce()).getAllCharacterList(page_ = 0)
    }

}