package com.example.service.user

import com.example.data.database.DatabaseFactory
import com.example.data.database.table.UserTable
import com.example.data.database.table.toUserDto
import com.example.domain.model.UserDto
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update

class UserServiceImpl : UserService {

    override suspend fun getUserById(userId: Int) = DatabaseFactory.dbQuery {
        UserTable.select { UserTable.userId.eq(userId) }
            .map { result ->
                result.toUserDto()
            }.singleOrNull()
    }

    override suspend fun updateUserInfo(userDto: UserDto) = DatabaseFactory.dbQuery {
        UserTable.update(
            { UserTable.userId eq userDto.userId }
        ) {
            it[fullName] = userDto.fullName
            it[email] = userDto.email
        }
    } > 0

}