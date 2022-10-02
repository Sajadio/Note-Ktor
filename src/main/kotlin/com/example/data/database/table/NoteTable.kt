package com.example.data.database.table

import com.example.domain.model.NoteDto
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object NoteTable : Table("notes") {
    val noteId = integer("note_id").autoIncrement()
    val userId = integer("user_id").references(ref = UserTable.userId, onDelete = ReferenceOption.CASCADE)
    val title = varchar("title", 256).nullable()
    val description = text("description").nullable()
    val color = varchar("color",256).nullable()
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    override val primaryKey = PrimaryKey(noteId)
}

fun ResultRow?.toNoteDto(): NoteDto? {
    return this?.let {
        NoteDto(
            noteId = this[NoteTable.noteId],
            userId = this[NoteTable.userId],
            title = this[NoteTable.title],
            description = this[NoteTable.description],
            color = this[NoteTable.color],
            createdAt = this[NoteTable.createdAt].toString(),
        )
    } ?: return null
}
