package com.dedany.marvelianos.di

import com.dedany.Marvelianos.usecase.CharacterUseCase
import com.dedany.Marvelianos.usecase.CharacterUseCaseImpl
import com.dedany.domain.repositories.CharacterRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideCharacterUseCase(characterRepository: CharacterRepository): CharacterUseCase {
        return CharacterUseCaseImpl(characterRepository)
    }
}