package com.srizan.cruiseman.di

import com.srizan.cruiseman.data.AuthenticationServiceImpl
import com.srizan.cruiseman.domain.repository.AuthenticationService
import org.koin.dsl.module


val repoModule = module {
    single<AuthenticationService> {
        AuthenticationServiceImpl(get(), get())
    }
}

