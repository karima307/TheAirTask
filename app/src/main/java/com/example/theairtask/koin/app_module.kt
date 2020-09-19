package com.example.theairtask.koin

import com.example.theairtask.data.remote.ApiConfig
import com.example.theairtask.data.remote.ApiService
import com.nmg.baseinfrastructure.data.remote.ApiServiceFactory
import com.nmg.baseinfrastructure.data.remote.ContextProviders
import org.koin.dsl.module

val appModule = module {
    single { ApiServiceFactory.getService<ApiService>(ApiConfig()) }
    single { ContextProviders.getInstance() }

}