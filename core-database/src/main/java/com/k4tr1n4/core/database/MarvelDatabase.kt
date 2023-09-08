package com.k4tr1n4.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.k4tr1n4.core.database.entity.CharacterEntity

@Database(
    entities = [CharacterEntity::class],
    version = 2,
    exportSchema = true
)
//@TypeConverters(value = [TypeResponseConverter::class])
abstract class MarvelDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao
}