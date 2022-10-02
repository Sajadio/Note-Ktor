package com.example.domain.model

data class NoteDto(
    val noteId: Int = 0,
    val userId: Int = 0,
    val title: String? = null,
    val description: String? = null,
    val color: String? = null,
    val createdAt: String = "",
)