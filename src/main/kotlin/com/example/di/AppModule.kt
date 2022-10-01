package com.example.di

import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.repository.note.NoteRepository
import com.example.repository.note.NoteRepositoryImpl
import com.example.service.auth.admin.AdminAuth
import com.example.service.auth.admin.AdminAuthImpl
import com.example.service.auth.user.UserAuth
import com.example.service.auth.user.UserAuthImpl
import com.example.service.note.NoteService
import com.example.service.note.NoteServiceImpl
import org.koin.dsl.module


val appModule = module {
    single<AdminAuth> {
        AdminAuthImpl()
    }
    single<UserAuth> {
        UserAuthImpl()
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get(), get())
    }

    single<NoteService>{
        NoteServiceImpl()
    }
    single<NoteRepository>{
        NoteRepositoryImpl(get())
    }

}