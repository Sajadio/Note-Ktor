package com.example.service.note

import com.example.data.database.DatabaseFactory
import com.example.data.database.table.NoteTable
import com.example.data.database.table.toNoteDto
import com.example.domain.model.NoteDto
import org.jetbrains.exposed.sql.*

class NoteServiceImpl : NoteService {

    override suspend fun addNote(noteDto: NoteDto) = DatabaseFactory.dbQuery {
        NoteTable.insert {
            it[userId] = noteDto.userId
            it[title] = noteDto.title
            it[description] = noteDto.description
        }.insertedCount > 0
    }

    override suspend fun getAllNotes() = DatabaseFactory.dbQuery {
        NoteTable.selectAll().map { result ->
            result.toNoteDto()
        }
    }

    override suspend fun getNoteByTitle(title: String) = DatabaseFactory.dbQuery {
        NoteTable.select {
            NoteTable.title eq title
        }.map { result ->
            result.toNoteDto()
        }.singleOrNull()
    }


    override suspend fun getNoteById(noteId: Int) = DatabaseFactory.dbQuery {
        NoteTable.select {
            NoteTable.noteId eq noteId
        }.map { result ->
            result.toNoteDto()
        }.singleOrNull()
    }

    override suspend fun deleteNoteById(noteId: Int) = DatabaseFactory.dbQuery {
        NoteTable.deleteWhere { NoteTable.noteId eq noteId } > 0
    }


    override suspend fun deleteAllNotes() = DatabaseFactory.dbQuery {
        NoteTable.deleteAll() > 0
    }


    override suspend fun updateNote(noteDto: NoteDto) = DatabaseFactory.dbQuery {
        NoteTable.update({
            NoteTable.userId eq noteDto.userId and (NoteTable.noteId eq noteDto.noteId)
        }) {
            it[title] = noteDto.title
            it[description] = noteDto.description
        }
    } > 0

    override suspend fun checkIdNoteIsExist(noteId: Int) = DatabaseFactory.dbQuery {
        NoteTable.select {
            NoteTable.noteId eq noteId
        }.empty()
    }
}