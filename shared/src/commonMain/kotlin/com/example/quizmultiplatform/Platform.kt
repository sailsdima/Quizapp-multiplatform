package com.example.quizmultiplatform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform