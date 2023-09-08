package com.k4tr1n4.core.database.di

import android.app.Application
import androidx.room.Room
import com.k4tr1n4.core.database.CharacterDao
import com.k4tr1n4.core.database.MarvelDatabase
import com.k4tr1n4.core.database.TypeResponseConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application,
        typeResponseConverter: TypeResponseConverter
    ): MarvelDatabase{
        return Room
            .databaseBuilder(application,MarvelDatabase::class.java,"Marvel.db")
            .fallbackToDestructiveMigration()
            //.addTypeConverter(typeResponseConverter)
            .build()
    }

    @Provides
    @Singleton
    fun provideCharacterDao(appDatabase: MarvelDatabase): CharacterDao {
        return appDatabase.characterDao()
    }

    /*@Provides
    @Singleton
    fun provideTypeResponseConverter(moshi: Moshi): TypeResponseConverter {
        return TypeResponseConverter(moshi)
    }*/
}