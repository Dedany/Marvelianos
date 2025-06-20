package com.dedany.marvelianos.data.datasource

import com.dedany.marvelianos.data.datasource.remote.api.MarvelService
import com.dedany.marvelianos.data.datasource.remote.dto.CharacterDto
import javax.inject.Inject

class MarvelRemoteDataSourceImpl @Inject constructor(
 private val marvelService: MarvelService
): MarvelRemoteDataSource {

    override suspend fun getCharacterById(id: Int): CharacterDto {
        return marvelService.getCharactersById(id).data.results.first()  // Suponiendo que la API devuelve una lista con 1 solo personaje
    }


    override suspend fun getCharacters(): List<CharacterDto> {
        return marvelService.getCharacters().data.results  // Extraemos la lista de personajes
    }


    override suspend fun searchCharacter(query: String): List<CharacterDto> {
       return marvelService.searchCharacter(query).data.results
    }
}