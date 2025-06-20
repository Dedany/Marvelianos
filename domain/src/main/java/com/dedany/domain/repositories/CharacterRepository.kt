package com.dedany.domain.repositories

import com.dedany.domain.entities.Character


interface CharacterRepository {

    suspend fun getCharacterById(id: Int): Character
    suspend fun getCharacters(): List<Character>
    suspend fun searchCharacter(query: String): List<Character>

}