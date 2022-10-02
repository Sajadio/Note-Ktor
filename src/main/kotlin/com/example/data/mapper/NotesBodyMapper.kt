package com.example.data.mapper

import com.example.domain.model.NoteDto
import com.example.domain.response.Note

object NotesBodyMapper : Mapper<List<NoteDto>, List<Note>> {

    override fun mapTo(input: List<NoteDto>): List<Note> {
        val notes = mutableListOf<Note>()
        input.forEach {
            notes.add(
                Note(
                    noteId = it.noteId,
                    userId = it.userId,
                    title = it.title,
                    description = it.description,
                    color = it.color,
                    createdAt = it.createdAt,
                )
            )
        }
        return notes
    }
}