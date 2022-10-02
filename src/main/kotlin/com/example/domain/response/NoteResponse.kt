package com.example.domain.response

import kotlinx.serialization.Serializable


@Serializable
data class NoteResponse(
    val status: String,
    val message: String? = null,
    val note: Note? = null
)

@Serializable
data class NotesResponse(
    val status: String,
    val message: String? = null,
    val totalResults: Int,
    val notes: List<Note>? = null
)

@Serializable
data class Note(
    val noteId: Int = 0,
    val userId: Int = 0,
    val title: String? = null,
    val description: String? = null,
    val createdAt: String = "",
)
