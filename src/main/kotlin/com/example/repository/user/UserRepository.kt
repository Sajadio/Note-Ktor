package com.example.repository.user

import com.example.domain.model.UserDto
import com.example.utils.Response

interface UserRepository {
    suspend fun getUserById(userId: Int): Response<Any>
    suspend fun updateUserInfo(user: UserDto): Response<Any>
}