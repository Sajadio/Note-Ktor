package com.example.repository.note

import com.example.domain.model.NoteDto
import com.example.domain.model.NoteParams
import com.example.utils.Response

interface NoteRepository {
    suspend fun addNote(noteDto: NoteDto): Response<Any>
    suspend fun getAllNotes(userId: Int): Response<Any>
    suspend fun getNoteByTitle(title: String,userId: Int): Response<Any>
    suspend fun getNoteById(noteId: Int,userId: Int): Response<Any>
    suspend fun deleteNoteById(noteId: Int,userId: Int): Response<Any>
    suspend fun deleteAllNotes(noteParams: NoteParams): Response<Any>
    suspend fun updateNote(noteDto: NoteDto): Response<Any>
    suspend fun checkIdNoteIsExist(noteId: Int): Boolean

}