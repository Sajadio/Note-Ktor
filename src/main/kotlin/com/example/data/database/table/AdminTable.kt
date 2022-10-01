package com.example.data.database.table

import com.example.domain.model.AdminDto
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object AdminTable : Table("admins") {
    val adminId = integer("admin_id").autoIncrement()
    val fullName = varchar("full_name", 256).nullable()
    val email = varchar("email", 256).nullable()
    val password = text("password").nullable()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(adminId)
}

fun ResultRow?.toAdminDto(): AdminDto? {
    return this?.let {
        AdminDto(
            adminId = this[AdminTable.adminId],
            fullName = this[AdminTable.fullName],
            email = this[AdminTable.email],
            password = this[AdminTable.password].toString(),
            createdAt = this[AdminTable.createdAt].toString(),
        )
    } ?: return null
}
