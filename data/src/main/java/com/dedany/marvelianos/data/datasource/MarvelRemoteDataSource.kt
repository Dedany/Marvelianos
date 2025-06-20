package com.dedany.marvelianos.data.datasource

import com.dedany.marvelianos.data.datasource.remote.dto.CharacterDto

interface MarvelRemoteDataSource {
    suspend fun getCharacterById(id: Int): CharacterDto
    suspend fun getCharacters(): List<CharacterDto>
    suspend fun searchCharacter(query: String): List<CharacterDto>
}