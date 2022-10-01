package com.example.routes.note

import com.example.domain.model.NoteDto
import com.example.domain.response.NoteResponse
import com.example.repository.note.NoteRepository
import com.example.routes.userId
import com.example.utils.ERROR
import com.example.utils.INVALID_AUTHENTICATION_TOKEN
import com.example.utils.OK
import com.example.utils.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.lang.Exception

fun Route.addNote(repository: NoteRepository) {
    post("note/add") {
        try {
            val request = call.receive<NoteDto>()
            if (request.userId != call.userId.toInt()) {
                call.respond(
                    HttpStatusCode.BadRequest, NoteResponse(
                        status = ERROR,
                        message = INVALID_AUTHENTICATION_TOKEN
                    )
                )
                return@post
            }

            when (val result = repository.addNote(request)) {
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
                    message = e.message
                )
            )
        }
    }
}