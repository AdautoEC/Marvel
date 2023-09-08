package com.k4tr1n4.core.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.squareup.moshi.Moshi
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [24])
abstract class LocalDataBase {
    lateinit var db: MarvelDatabase

    @Before
    fun initDB() {
        //val moshi = Moshi.Builder().build()
        db = Room.inMemoryDatabaseBuilder(getApplicationContext(), MarvelDatabase::class.java)
            .allowMainThreadQueries()
            //.addTypeConverter(TypeResponseConverter(moshi))
            .build()
    }

    @After
    fun closeDB() {
        db.close()
    }
}