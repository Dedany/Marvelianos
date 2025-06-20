package com.dedany.marvelianos.data.repositories

import com.dedany.domain.repositories.CharacterRepository
import com.dedany.marvelianos.data.datasource.MarvelRemoteDataSource
import com.dedany.marvelianos.data.datasource.remote.dto.CharacterDto
import com.dedany.domain.entities.Character
import javax.inject.Inject


class CharacterRepositoryImpl @Inject constructor(
private val CharacterRemoteDataSource: MarvelRemoteDataSource
) : CharacterRepository {

    override suspend fun getCharacterById(id: Int): Character {
        val remoteCharacter = CharacterRemoteDataSource.getCharacterById(id)
        return remoteCharacter.toDomain()
    }

    override suspend fun getCharacters(): List<Character> {
        return CharacterRemoteDataSource.getCharacters().map { it.toDomain() }
    }

    override suspend fun searchCharacter(query: String): List<Character> {
       return CharacterRemoteDataSource.searchCharacter(query).map { it.toDomain() }
    }
}

fun CharacterDto.toDomain(): Character {
    return Character(
        id = this.id,
        name = this.name,
        description = this.description,
        thumbnail = "${this.thumbnail.path}.${this.thumbnail.extension}", // junta path + extension
        comics = this.comics.items.map { it.name },  // solo el nombre del cómic
        series = this.series.items.map { it.name }   // solo el nombre de la serie
    )
}

