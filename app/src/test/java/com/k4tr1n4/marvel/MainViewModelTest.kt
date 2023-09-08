package com.k4tr1n4.marvel

import app.cash.turbine.test
import com.k4tr1n4.core.data.repository.MainRepositoryImpl
import com.k4tr1n4.core.database.CharacterDao
import com.k4tr1n4.core.database.entity.mapper.asEntity
import com.k4tr1n4.core.network.service.MarvelClient
import com.k4tr1n4.core.network.service.MarvelService
import com.k4tr1n4.core.test.MainCourotinesRule
import com.k4tr1n4.core.test.MockUtil
import com.k4tr1n4.marvel.ui.main.MainViewModel
import com.nhaarman.mockitokotlin2.atLeastOnce
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.time.DurationUnit
import kotlin.time.toDuration

class MainViewModelTest {


    private lateinit var viewModel: MainViewModel
    private lateinit var mainRepository: MainRepositoryImpl

    private val marvelService: MarvelService = mock()
    private val marvelClient: MarvelClient = MarvelClient(marvelService)
    private val characterDao: CharacterDao = mock()

    @get:Rule
    val coroutinesRule = MainCourotinesRule()

    @Before
    fun setup() {
        mainRepository = MainRepositoryImpl(marvelClient, characterDao, coroutinesRule.testDispatcher)
        viewModel = MainViewModel(mainRepository)
    }

    @Test
    fun fetchMarvelListTest() = runTest {
        val mockData = MockUtil.mockCharacterList()
        whenever(characterDao.getCharacterList(page_ = 0)).thenReturn(mockData.asEntity())
        whenever(characterDao.getAllCharacterList(page_= 0)).thenReturn(mockData.asEntity())

        mainRepository.fetchMarvelList(
            page = 0,
            onStart = {},
            onComplete = {},
            onError = {}
        ).test(2.toDuration(DurationUnit.SECONDS)) {
            val exceptItem = awaitItem()
            Assert.assertEquals(exceptItem[0].id, 1011334)
            Assert.assertEquals(exceptItem[0].name, "3-D Man")
            Assert.assertEquals(exceptItem[0].description, "")
            Assert.assertEquals(exceptItem, MockUtil.mockCharacterList())
            awaitComplete()
        }

        viewModel.fetchNextMarvelList()

        verify(characterDao, atLeastOnce()).getCharacterList(page_ = 0)
    }
}