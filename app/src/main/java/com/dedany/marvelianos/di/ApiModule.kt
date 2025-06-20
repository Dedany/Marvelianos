package com.dedany.marvelianos.di

import com.dedany.marvelianos.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Provides
    @Singleton
    @Named("apikey")
    fun provideApiKey(): String = BuildConfig.MARVEL_API_KEY

    @Provides
    @Singleton
    @Named("publickey")
    fun providePublicKey(): String = BuildConfig.MARVEL_PUBLIC_KEY

    @Provides
    @Singleton
    @Named("privatekey")
    fun providePrivateKey(): String = BuildConfig.MARVEL_PRIVATE_KEY

}