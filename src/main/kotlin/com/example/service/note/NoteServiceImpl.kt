package com.example.service.note

import com.example.data.database.DatabaseFactory
import com.example.data.database.table.NoteTable
import com.example.data.database.table.toNoteDto
import com.example.domain.model.NoteDto
import com.example.domain.model.NoteParams
import org.jetbrains.exposed.sql.*

class NoteServiceImpl : NoteService {

    override suspend fun addNote(noteDto: NoteDto) = DatabaseFactory.dbQuery {
        NoteTable.insert {
            it[userId] = noteDto.userId
            it[title] = noteDto.title
            it[description] = noteDto.description
        }.insertedCount > 0
    }

    override suspend fun getAllNotes(userId: Int) = DatabaseFactory.dbQuery {
        NoteTable.selectAll().andWhere {
            NoteTable.userId eq userId
        }.map { result ->
            result.toNoteDto()
        }
    }

    override suspend fun getNoteByTitle(title: String, userId: Int) = DatabaseFactory.dbQuery {
        NoteTable.select {
            NoteTable.userId eq userId and (NoteTable.title like title)
        }.toList()
    }


    override suspend fun getNoteById(noteId: Int, userId: Int) = DatabaseFactory.dbQuery {
        NoteTable.select {
            NoteTable.userId eq userId and
                    (NoteTable.noteId eq noteId)
        }.map { result ->
            result.toNoteDto()
        }.singleOrNull()
    }

    override suspend fun deleteNoteById(noteId: Int, userId: Int) = DatabaseFactory.dbQuery {
        NoteTable.deleteWhere {
            NoteTable.userId eq userId and (NoteTable.noteId eq noteId)
        } > 0
    }


    override suspend fun deleteAllNotes(noteParams: NoteParams): Boolean {
        var result = 0
        DatabaseFactory.dbQuery {
            noteParams.notesId.forEach { id ->
                result = NoteTable.deleteWhere {
                    NoteTable.userId eq noteParams.userId and (NoteTable.noteId eq id)
                }
            }
        }
        return result > 0
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