package com.dedany.domain.entities

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val thumbnail: String,
    val comics: List<String>,
    val series: List<String>
)
