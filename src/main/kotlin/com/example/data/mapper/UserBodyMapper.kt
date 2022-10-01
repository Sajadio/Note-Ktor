package com.example.data.mapper

import com.example.domain.model.UserDto
import com.example.domain.response.User

object UserBodyMapper : Mapper<UserDto, User> {
    override fun mapTo(input: UserDto): User {
        return User(
            userId = input.userId,
            fullName = input.fullName,
            email = input.email,
            createdAt = input.createdAt
        )
    }
}