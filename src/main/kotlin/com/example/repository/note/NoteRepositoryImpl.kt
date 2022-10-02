package com.example.repository.note

import com.example.domain.model.NoteDto
import com.example.domain.model.NoteParams
import com.example.service.note.NoteService
import com.example.utils.*
import io.ktor.http.*

class NoteRepositoryImpl(
    private val noteService: NoteService
) : NoteRepository {

    override suspend fun addNote(noteDto: NoteDto) = if (noteService.addNote(noteDto))
        checkResponseStatus(
            message = SUCCESS,
            statusCode = HttpStatusCode.OK,
        )
    else
        checkResponseStatus(
            message = ERROR,
            statusCode = HttpStatusCode.BadRequest,
        )

    override suspend fun getAllNotes(userId: Int): Response<Any> {
        val notes = noteService.getAllNotes(userId)
        return if (notes.isNotEmpty())
            checkResponseStatus(
                message = SUCCESS,
                statusCode = HttpStatusCode.OK,
                data = notes
            )
        else checkResponseStatus(
            message = EMPTY_RESULT,
            statusCode = HttpStatusCode.BadRequest
        )
    }

    override suspend fun getNoteByTitle(title: String,userId: Int): Response<Any> {
        val result = noteService.getNoteByTitle(title,userId)
        return if (result.isNotEmpty()) {
            checkResponseStatus(
                message = SUCCESS,
                statusCode = HttpStatusCode.OK,
                data = result
            )
        } else
            checkResponseStatus(
                message = EMPTY_RESULT,
                statusCode = HttpStatusCode.NotFound
            )
    }

    override suspend fun getNoteById(noteId: Int,userId: Int) =
        noteService.getNoteById(noteId,userId)?.let { note ->
            checkResponseStatus(
                message = SUCCESS,
                statusCode = HttpStatusCode.OK,
                data = note
            )
        } ?: checkResponseStatus(
            message = EMPTY_RESULT,
            statusCode = HttpStatusCode.NotFound
        )

    override suspend fun deleteNoteById(noteId: Int,userId: Int) = if (noteService.deleteNoteById(noteId,userId))
        checkResponseStatus(
            message = SUCCESS,
            statusCode = HttpStatusCode.OK,
        )
    else
        checkResponseStatus(
            message = ERROR,
            statusCode = HttpStatusCode.BadRequest,
        )

    override suspend fun deleteAllNotes(noteParams: NoteParams) = if (noteService.deleteAllNotes(noteParams))
        checkResponseStatus(
            message = SUCCESS,
            statusCode = HttpStatusCode.OK,
        )
    else
        checkResponseStatus(
            message = ERROR,
            statusCode = HttpStatusCode.BadRequest,
        )

    override suspend fun updateNote(noteDto: NoteDto) = if (noteService.updateNote(noteDto))
        checkResponseStatus(
            message = SUCCESS,
            statusCode = HttpStatusCode.OK,
        )
    else
        checkResponseStatus(
            message = ERROR,
            statusCode = HttpStatusCode.BadRequest,
        )

    override suspend fun checkIdNoteIsExist(noteId: Int) = noteService.checkIdNoteIsExist(noteId)
}