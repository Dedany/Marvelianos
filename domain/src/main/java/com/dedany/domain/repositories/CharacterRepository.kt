package com.dedany.domain.repositories

interface CharacterRepository {

    suspend fun getCharacterById(id: Int): Character
    suspend fun getCharacters(): List<Character>
    suspend fun searchCharacter(query: String): List<Character>

}