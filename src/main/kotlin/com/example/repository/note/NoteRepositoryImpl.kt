package com.example.repository.note

import com.example.domain.model.NoteDto
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

    override suspend fun getAllNotes(): Response<Any> {
        val notes = noteService.getAllNotes()
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

    override suspend fun getNoteByTitle(title: String) =
        noteService.getNoteByTitle(title)?.let { note ->
            checkResponseStatus(
                message = SUCCESS,
                statusCode = HttpStatusCode.OK,
                data = note
            )
        } ?: checkResponseStatus(
            message = EMPTY_RESULT,
            statusCode = HttpStatusCode.NotFound
        )

    override suspend fun getNoteById(noteId: Int) =
        noteService.getNoteById(noteId)?.let { note ->
            checkResponseStatus(
                message = SUCCESS,
                statusCode = HttpStatusCode.OK,
                data = note
            )
        } ?: checkResponseStatus(
            message = EMPTY_RESULT,
            statusCode = HttpStatusCode.NotFound
        )

    override suspend fun deleteNoteById(noteId: Int) = if (noteService.deleteNoteById(noteId))
        checkResponseStatus(
            message = SUCCESS,
            statusCode = HttpStatusCode.OK,
        )
    else
        checkResponseStatus(
            message = ERROR,
            statusCode = HttpStatusCode.BadRequest,
        )

    override suspend fun deleteAllNotes() = if (noteService.deleteAllNotes())
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
}