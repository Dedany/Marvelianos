package com.dedany.Marvelianos.usecase

import com.dedany.domain.repositories.CharacterRepository
import com.dedany.domain.entities.Character
import javax.inject.Inject

class CharacterUseCaseImpl @Inject constructor(
    private val characterRepository: CharacterRepository

): CharacterUseCase  {
    override suspend fun getCharacterById(id: Int): Character {
        return characterRepository.getCharacterById(id)
    }

    override suspend fun getCharacters(): List<Character> {
        return characterRepository.getCharacters().sortedBy { it.name }
    }

    override suspend fun searchCharacter(query: String): List<Character> {
        return characterRepository.searchCharacter(query)
    }
}