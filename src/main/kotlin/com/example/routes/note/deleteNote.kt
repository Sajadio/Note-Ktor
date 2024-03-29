package com.example.routes.note

import com.example.domain.response.NoteResponse
import com.example.repository.note.NoteRepository
import com.example.routes.userId
import com.example.utils.ERROR
import com.example.utils.MESSAGE_NOTE_NAME
import com.example.utils.OK
import com.example.utils.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteNote(repository: NoteRepository) {
    get("note/delete") {
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

            when (val result = repository.deleteNoteById(noteId,call.userId.toInt())) {
                is Response.SuccessResponse -> {
                    call.respond(
                        result.statusCode, NoteResponse(
                            status = OK,
                            message = result.message,
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