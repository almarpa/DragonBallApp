package com.almarpa.dragonballapp.di

import com.almarpa.dragonballapp.domain.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class DiHelper : KoinComponent{
    val repository : Repository by inject()
}