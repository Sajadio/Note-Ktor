package com.example.repository.user

import com.example.domain.model.UserDto
import com.example.service.user.UserService
import com.example.utils.*
import io.ktor.http.*

class UserRepositoryImpl(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUserById(userId: Int) =
        userService.getUserById(userId)?.let { user ->
            checkResponseStatus(
                message = SUCCESS,
                statusCode = HttpStatusCode.OK,
                data = user
            )
        } ?: checkResponseStatus(
            message = MESSAGE_USER_ID,
            statusCode = HttpStatusCode.NotFound
        )


    override suspend fun updateUserInfo(user: UserDto) = if (userService.updateUserInfo(user))
        checkResponseStatus(
            message = SUCCESS,
            statusCode = HttpStatusCode.OK,
        )
    else
        checkResponseStatus(
            message = GENERIC_ERROR,
            statusCode = HttpStatusCode.BadRequest,
        )

}