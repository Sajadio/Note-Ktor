package com.example.domain.response

import kotlinx.serialization.Serializable


@Serializable
data class UserResponse(
    val status: String,
    val message: String? = null,
    val user: User? = null
)

@Serializable
data class User(
    val userId: Int = 0,
    val fullName: String? = null,
    val email: String? = null,
    val createdAt: String = ""
)
