package com.example.routes.note

import com.example.data.mapper.NoteBodyMapper
import com.example.domain.model.NoteDto
import com.example.domain.response.NoteResponse
import com.example.repository.note.NoteRepository
import com.example.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.getNoteDetails(repository: NoteRepository) {
    get("note") {
        try {
            val noteId = call.request.queryParameters["noteId"]?.toIntOrNull()

            if (noteId == null) {
                call.respond(
                    HttpStatusCode.BadRequest, NoteResponse(
                        status = ERROR,
                        message = "The note id is null"
                    )
                )
                return@get
            }
            if (repository.checkIdNoteIsExist(noteId)) {
                call.respond(
                    HttpStatusCode.BadRequest, NoteResponse(
                        status = ERROR,
                        message = MESSAGE_NOTE_NAME
                    )
                )
                return@get
            }

            when (val result = repository.getNoteById(noteId)) {
                is Response.SuccessResponse -> {
                    val noteDto = result.data as NoteDto
                    val note = NoteBodyMapper.mapTo(noteDto)
                    call.respond(
                        result.statusCode, NoteResponse(
                            status = OK,
                            message = result.message,
                            note = note
                        )
                    )
                }

                is Response.ErrorResponse -> {
                    call.respond(
                        result.statusCode, NoteResponse(
                            status = ERROR,
                            message = result.message,
                        )
                    )
                }
            }

        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.BadRequest, NoteResponse(
                    status = ERROR,
                    message = e.message.toString()
                )
            )
        }
    }
}