package com.example.service.user

import com.example.domain.model.UserDto

interface UserService {
    suspend fun getUserById(userId: Int): UserDto?
    suspend fun updateUserInfo(userDto: UserDto): Boolean
}