package com.dedany.marvelianos.data.repositories

import com.dedany.domain.repositories.CharacterRepository

class CharacterRepositoryImpl : CharacterRepository {
    override suspend fun getCharacterById(id: Int): Character {
        TODO("Not yet implemented")
    }

    override suspend fun getCharacters(): List<Character> {
        TODO("Not yet implemented")
    }

    override suspend fun searchCharacter(query: String): List<Character> {
        TODO("Not yet implemented")
    }
}