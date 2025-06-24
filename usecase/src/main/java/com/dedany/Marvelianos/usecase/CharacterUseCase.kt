package com.dedany.Marvelianos.usecase
import com.dedany.domain.entities.Character


interface CharacterUseCase {

    suspend fun getCharacterById(id: Int): Character
    suspend fun getCharacters(): List<Character>
    suspend fun searchCharacter(query: String): List<Character>

}