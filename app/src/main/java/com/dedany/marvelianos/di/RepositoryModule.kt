package com.dedany.marvelianos.di

import com.dedany.domain.repositories.CharacterRepository
import com.dedany.marvelianos.data.datasource.MarvelRemoteDataSource
import com.dedany.marvelianos.data.repositories.CharacterRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideCharacterRepository(marvelRemoteDataSource: MarvelRemoteDataSource): CharacterRepository {
        return CharacterRepositoryImpl(marvelRemoteDataSource)
    }

}