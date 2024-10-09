package com.almarpa.dragonballapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform