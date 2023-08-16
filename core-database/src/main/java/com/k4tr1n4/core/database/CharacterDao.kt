package com.k4tr1n4.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.k4tr1n4.core.database.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(characterList: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity WHERE page = :page_;")
    suspend fun getCharacterList(page_: Int): List<CharacterEntity>

    @Query("SELECT * FROM CharacterEntity WHERE page <= :page_;")
    suspend fun getAllCharacterList(page_: Int): List<CharacterEntity>
}