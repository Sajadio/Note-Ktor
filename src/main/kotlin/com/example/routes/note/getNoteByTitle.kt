package com.example.routes.note

import com.example.data.database.table.toNoteDto
import com.example.data.mapper.NotesBodyMapper
import com.example.domain.response.NotesResponse
import com.example.repository.note.NoteRepository
import com.example.utils.ERROR
import com.example.utils.OK
import com.example.utils.Response
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.jetbrains.exposed.sql.ResultRow

fun Route.getNoteByTitle(repository: NoteRepository) {
    get("note/{title}") {
        try {
            val noteTitle = call.parameters["title"]

            if (noteTitle == null) {
                call.respond(
                    HttpStatusCode.BadRequest, NotesResponse(
                        status = ERROR,
                        message = "The note title is null",
                        totalResults = 0
                    )
                )
                return@get
            }
            when (val result = repository.getNoteByTitle(noteTitle)) {
                is Response.SuccessResponse -> {
                    val noteDto = (result.data as List<ResultRow>).map {
                        it.toNoteDto()!!
                    }
                    val notes = NotesBodyMapper.mapTo(noteDto)
                    call.respond(
                        result.statusCode, NotesResponse(
                            status = OK,
                            message = result.message,
                            totalResults = notes.size,
                            notes = notes
                        )
                    )
                }

                is Response.ErrorResponse -> {
                    call.respond(
                        result.statusCode, NotesResponse(
                            status = ERROR,
                            message = result.message,
                            totalResults = 0,
                        )
                    )
                }
            }

        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.BadRequest, NotesResponse(
                    status = ERROR,
                    message = e.message.toString(),
                    totalResults = 0,
                )
            )
        }
    }
}