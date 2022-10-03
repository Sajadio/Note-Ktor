package com.example.routes.user

import com.example.domain.model.UserDto
import com.example.repository.user.UserRepository
import com.example.routes.userId
import com.example.utils.*
import com.example.domain.response.UserResponse
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.updateUserInfo(repository: UserRepository) {
    put("/update") {
        try {
            val user = call.receive<UserDto>()

            if (user.userId != call.userId.toInt()) {
                call.respond(
                    HttpStatusCode.BadRequest, UserResponse(
                        status = ERROR,
                        message = INVALID_AUTHENTICATION_TOKEN
                    )
                )
                return@put
            }
            when (val result = repository.updateUserInfo(user)) {
                is Response.SuccessResponse -> {
                    call.respond(
                        result.statusCode, UserResponse(
                            status = OK,
                            message = result.message
                        )
                    )
                }

                is Response.ErrorResponse -> {
                    call.respond(
                        HttpStatusCode.InternalServerError, UserResponse(
                            status = ERROR,
                            message = result.message,
                        )
                    )
                }
            }
        } catch (e: Exception) {
            call.respond(
                HttpStatusCode.BadRequest, UserResponse(
                    status = ERROR,
                    message = e.message
                )
            )
        }
    }
}