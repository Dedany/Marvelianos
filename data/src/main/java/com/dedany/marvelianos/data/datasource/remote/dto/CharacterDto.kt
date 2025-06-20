package com.dedany.marvelianos.data.datasource.remote.dto


import kotlinx.serialization.Serializable

@Serializable
data class MarvelApiResponseDto(
    val code: Int,
    val status: String,
    val data: DataContainerDto
)

@Serializable
data class DataContainerDto(
    val offset: Int,
    val limit: Int,
    val total: Int,
    val count: Int,
    val results: List<CharacterDto>
)

@Serializable
data class CharacterDto(
    val id: Int,
    val name: String,
    val description: String,
    val modified: String,
    val thumbnail: ThumbnailDto,
    val resourceURI: String,
    val comics: ComicsDto,
    val series: SeriesDto
)

@Serializable
data class ThumbnailDto(
    val path: String,
    val extension: String
)

@Serializable
data class ComicsDto(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemDto>,
    val returned: Int
)

@Serializable
data class SeriesDto(
    val available: Int,
    val collectionURI: String,
    val items: List<ItemDto>
)

@Serializable
data class ItemDto(
    val resourceURI: String,
    val name: String
)
