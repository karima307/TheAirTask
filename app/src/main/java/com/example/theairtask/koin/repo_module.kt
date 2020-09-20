package com.example.theairtask.koin

import com.example.theairtask.modules.tv_details.TVDetailsRepo
import com.example.theairtask.modules.tv_list.TVListRepo
import org.koin.dsl.module

val repoModule = module {
single {
    TVListRepo(get(),get())
}
    single {
    TVDetailsRepo(get(),get())
}
}