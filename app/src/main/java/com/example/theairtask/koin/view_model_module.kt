package com.example.theairtask.koin

import com.example.theairtask.modules.session.SessionViewModel
import com.example.theairtask.modules.tv_details.TVDetailsViewModel
import com.example.theairtask.modules.tv_list.TVListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModel = module {
    viewModel {
        TVListViewModel(get())
    }
    viewModel {
        TVDetailsViewModel(get())
    }
    single {
        SessionViewModel(get())
    }
}