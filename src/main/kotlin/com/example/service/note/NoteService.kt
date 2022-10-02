package com.example.service.note

import com.example.domain.model.NoteDto
import com.example.domain.model.NoteParams
import org.jetbrains.exposed.sql.ResultRow

interface NoteService {
    suspend fun addNote(noteDto: NoteDto): Boolean
    suspend fun getAllNotes(userId: Int): List<NoteDto?>
    suspend fun getNoteByTitle(title: String,userId: Int): List<ResultRow>
    suspend fun getNoteById(noteId: Int,userId: Int): NoteDto?
    suspend fun deleteNoteById(noteId: Int, userId: Int): Boolean
    suspend fun deleteAllNotes(noteParams: NoteParams): Boolean
    suspend fun updateNote(noteDto: NoteDto): Boolean
    suspend fun checkIdNoteIsExist(noteId: Int): Boolean
}