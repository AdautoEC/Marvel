package com.k4tr1n4.core.test

import com.k4tr1n4.core.model.Character

object MockUtil {
    /*
    *  var page: Int = 0,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "modified") val modified: String?,
    @field:Json(name = "resourceURI") val resourceURI: String?*/
    fun mockCharacter() = Character(
        page = 0,
        id = 1011334,
        name = "3-D Man",
        description = "",
        modified = "2014-04-29T14:18:17-0400",
        resourceURI = "http://gateway.marvel.com/v1/public/characters/1011334"
    )

    fun mockCharacterList() = listOf(mockCharacter())
}