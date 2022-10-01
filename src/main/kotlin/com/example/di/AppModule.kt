package com.example.di

import com.example.repository.auth.AuthRepository
import com.example.repository.auth.AuthRepositoryImpl
import com.example.service.auth.user.UserAuth
import com.example.service.auth.user.UserAuthImpl
import org.koin.dsl.module


val appModule = module {
//    single<AdminAuth> {
//        AdminAuthImpl()
//    }
    single<UserAuth> {
        UserAuthImpl()
    }
    single<AuthRepository> {
        AuthRepositoryImpl(get())
    }

}