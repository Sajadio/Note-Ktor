package com.example.data.mapper

import com.example.domain.model.NoteDto
import com.example.domain.response.Note

object NoteBodyMapper : Mapper<NoteDto, Note> {

    override fun mapTo(input: NoteDto) = Note(
        noteId = input.noteId,
        userId = input.userId,
        title = input.title,
        description = input.description,
        createdAt = input.createdAt,
    )
}