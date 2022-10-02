package com.example.repository.note

import com.example.domain.model.NoteDto
import com.example.utils.Response

interface NoteRepository {
    suspend fun addNote(noteDto: NoteDto): Response<Any>
    suspend fun getAllNotes(): Response<Any>
    suspend fun getNoteByTitle(title: String): Response<Any>
    suspend fun getNoteById(noteId: Int): Response<Any>
    suspend fun deleteNoteById(noteId: Int): Response<Any>
    suspend fun deleteAllNotes(): Response<Any>
    suspend fun updateNote(noteDto: NoteDto): Response<Any>
    suspend fun checkIdNoteIsExist(noteId: Int): Boolean

}