package com.waspbyte.equationizer

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform