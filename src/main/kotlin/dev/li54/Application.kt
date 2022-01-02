package dev.li54

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import dev.li54.plugins.*

fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}
