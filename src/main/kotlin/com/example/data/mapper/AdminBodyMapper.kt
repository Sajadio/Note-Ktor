package com.example.data.mapper

import com.example.domain.model.AdminDto
import com.example.domain.response.Admin

object AdminBodyMapper : Mapper<AdminDto, Admin> {
    override fun mapTo(input: AdminDto): Admin {
        return Admin(
            adminId = input.adminId,
            fullName = input.fullName,
            email = input.email,
            createdAt = input.createdAt
        )
    }
}