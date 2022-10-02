package com.example.plugins

import com.example.di.appModule
import com.example.repository.auth.AuthRepository
import com.example.repository.note.NoteRepository
import com.example.routes.auth.login
import com.example.routes.auth.signUp
import com.example.routes.note.addNote
import com.example.routes.note.deleteNote
import com.example.routes.note.getNoteDetails
import com.example.routes.note.updateNote
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun Application.configureRouting() {
    install(Koin) {
        modules(appModule)
    }
    routing {
        val authRepo by inject<AuthRepository>()
        signUp(authRepo)
        login(authRepo)

        val noteRepo by inject<NoteRepository>()
        route("user") {
            authenticate("auth-user") {
                addNote(noteRepo)
                updateNote(noteRepo)
                getNoteDetails(noteRepo)
                deleteNote(noteRepo)
            }
        }
    }
}
