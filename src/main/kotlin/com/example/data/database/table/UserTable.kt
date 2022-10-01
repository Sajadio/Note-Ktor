package com.example.data.database.table

import com.example.domain.model.UserDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object UserTable : Table("users") {
    val userId = integer("user_id").autoIncrement()
    val fullName = varchar("full_name", 256).nullable()
    val email = varchar("email", 256).nullable()
    val password = text("password").nullable()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(userId)
}

fun ResultRow?.toUserDto(): UserDto? {
    return this?.let {
        UserDto(
            userId = this[UserTable.userId],
            fullName = this[UserTable.fullName],
            email = this[UserTable.email],
            password = this[UserTable.password].toString(),
            createdAt = this[UserTable.createdAt].toString(),
        )
    } ?: return null
}
