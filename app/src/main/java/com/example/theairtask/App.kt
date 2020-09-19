package com.example.theairtask

import android.app.Application
import android.content.Context
import com.example.theairtask.koin.appModule
import com.example.theairtask.koin.repoModule
import com.example.theairtask.koin.viewModel
import com.nmg.baseinfrastructure.App
import org.koin.core.module.Module

class App : App() {
    override fun getAndroidContext(): Context {
        return this
    }

    override fun getModulesList(): List<Module> {
        return listOf(appModule, repoModule, viewModel)
    }

    companion object{
        lateinit var appInstance: App

    }
}