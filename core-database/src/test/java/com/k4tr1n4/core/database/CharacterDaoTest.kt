package com.k4tr1n4.core.database

import com.k4tr1n4.core.database.entity.mapper.asEntity
import com.k4tr1n4.core.test.MockUtil.mockCharacterList
import com.k4tr1n4.core.test.MockUtil.mockCharacter
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24])
class CharacterDaoTest : LocalDataBase() {

    private lateinit var characterDao: CharacterDao

    @Before
    fun init() {
        characterDao = db.characterDao()
    }

    @Test
    fun insertAndLoadCharacterListTest() = runBlocking {
        val mockDataList = mockCharacterList().asEntity()
        characterDao.insertCharacterList(mockDataList)

        val loadFromDB = characterDao.getCharacterList(page_ = 0)
        assertThat(loadFromDB.toString(), `is`(mockDataList.toString()))

        val mockData = listOf(mockCharacter()).asEntity()[0]
        assertThat(loadFromDB[0].toString(), `is`(mockData.toString()))
    }
}