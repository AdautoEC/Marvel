package com.k4tr1n4.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CharacterEntity(
    var page: Int = 0,
    val id: Int?,
    @PrimaryKey val name: String,
    val description: String?,
    val modified: String?,
    val resourceURI: String?
)
