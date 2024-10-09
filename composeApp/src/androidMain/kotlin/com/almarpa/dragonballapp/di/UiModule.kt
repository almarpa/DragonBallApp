package com.almarpa.dragonballapp.di

import com.almarpa.dragonballapp.ui.detail.DetailViewModel
import com.almarpa.dragonballapp.ui.home.HomeViewModel
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val uiModule = module {
    factoryOf(::HomeViewModel)
    factoryOf(::DetailViewModel)
}