package com.dedany.marvelianos.data.datasource.remote.api

import com.dedany.marvelianos.data.datasource.remote.dto.MarvelApiResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelService {

    @GET("characters")
    suspend fun getCharacters(
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
        @Query("nameStartsWith") name: String? = null,
    ): MarvelApiResponseDto

    @GET("characters/{id}")
    suspend fun getCharactersById(
        @retrofit2.http.Path("id") id: Int,
    ): MarvelApiResponseDto

    @GET("characters")
    suspend fun searchCharacter(
        @Query("nameStartsWith") name: String,
        @Query("limit") limit: Int = 20,
        @Query("offset") offset: Int = 0,
    ): MarvelApiResponseDto
}