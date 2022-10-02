package com.example.service.note

import com.example.domain.model.NoteDto
import org.jetbrains.exposed.sql.ResultRow

interface NoteService {
    suspend fun addNote(noteDto: NoteDto): Boolean
    suspend fun getAllNotes(): List<NoteDto?>
    suspend fun getNoteByTitle(title: String): List<ResultRow>
    suspend fun getNoteById(noteId: Int): NoteDto?
    suspend fun deleteNoteById(noteId: Int): Boolean
    suspend fun deleteAllNotes(): Boolean
    suspend fun updateNote(noteDto: NoteDto): Boolean
    suspend fun checkIdNoteIsExist(noteId: Int): Boolean
}