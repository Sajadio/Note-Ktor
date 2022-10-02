package com.example.routes.note

import com.example.domain.model.NoteParams
import com.example.domain.response.NoteResponse
import com.example.repository.note.NoteRepository
import com.example.routes.userId
import com.example.utils.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.deleteAllNote(repository: NoteRepository) {
    get("notes/delete") {
        try {
            val noteParams = call.receive<NoteParams>()
            if (noteParams.userId != call.userId.toInt()) {
                call.respond(
                    HttpStatusCode.BadRequest, NoteResponse(
                        status = ERROR,
                        message = INVALID_AUTHENTICATION_TOKEN
                    )
                )
                return@get
            }
            when (val result = repository.deleteAllNotes(noteParams)) {
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