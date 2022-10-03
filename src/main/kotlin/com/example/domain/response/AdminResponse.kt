package com.example.domain.response

import kotlinx.serialization.Serializable


@Serializable
data class AdminResponse(
    val status: String,
    val message: String? = null,
    val admin: Admin? = null,
)

@Serializable
data class AdminsResponse(
    val status: String,
    val message: String? = null,
    val users: List<User>? = null
)

@Serializable
data class Admin(
    val adminId: Int = 0,
    val fullName: String? = null,
    val email: String? = null,
    val createdAt: String = ""
)
