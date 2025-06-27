package com.dedany.marvelianos.di

import com.dedany.marvelianos.data.datasource.MarvelRemoteDataSource
import com.dedany.marvelianos.data.datasource.MarvelRemoteDataSourceImpl
import com.dedany.marvelianos.data.datasource.remote.api.MarvelService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    fun provideMarvelRemoteDataSource(marvelService: MarvelService): MarvelRemoteDataSource{
        return MarvelRemoteDataSourceImpl(marvelService)

    }
}