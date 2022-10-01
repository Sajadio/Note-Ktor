package com.example.plugins

import com.example.di.appModule
import com.example.repository.auth.AuthRepository
import com.example.routes.auth.login
import com.example.routes.auth.signUp
import io.ktor.server.routing.*
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun Application.configureRouting() {
    install(Koin) {
        modules(appModule)
    }
    routing {
        val repository by inject<AuthRepository>()
        signUp(repository)
        login(repository)
    }
}
