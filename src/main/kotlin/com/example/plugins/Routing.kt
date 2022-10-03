package com.example.plugins

import com.example.di.appModule
import com.example.repository.auth.AuthRepository
import com.example.repository.note.NoteRepository
import com.example.repository.user.UserRepository
import com.example.routes.auth.login
import com.example.routes.auth.signUp
import com.example.routes.note.*
import com.example.routes.user.getUserInfo
import com.example.routes.user.updateUserInfo
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

        val userRepo by inject<UserRepository>()
        val noteRepo by inject<NoteRepository>()
        route("user") {
            authenticate("auth-user") {
                getUserInfo(userRepo)
                updateUserInfo(userRepo)

                addNote(noteRepo)
                getAllNotes(noteRepo)
                getNoteDetails(noteRepo)
                getNoteByTitle(noteRepo)
                deleteNote(noteRepo)
                deleteAllNote(noteRepo)
                updateNote(noteRepo)
            }
        }
    }
}
