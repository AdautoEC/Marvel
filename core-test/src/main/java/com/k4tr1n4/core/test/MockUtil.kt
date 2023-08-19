package com.k4tr1n4.core.test

import com.k4tr1n4.core.model.Character
import com.k4tr1n4.core.model.FetchAllResponse

object MockUtil {
    fun mockCharacter() = Character(
        page = 0,
        id = 1011334,
        name = "3-D Man",
        description = "",
        modified = "2014-04-29T14:18:17-0400",
        resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334"
    )

    fun mockCharacterList() = listOf(mockCharacter())

    fun mockFetchAllResponse() = FetchAllResponse(
        offset = 0,
        limit = 20,
        total = 1562,
        count = 20,
        results = mockCharacterList()
    )
}