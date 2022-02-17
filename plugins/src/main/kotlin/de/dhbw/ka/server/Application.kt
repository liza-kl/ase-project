package de.dhbw.ka.server

import de.dhbw.ka.configureRouting
import de.dhbw.ka.controllers.getInstrumentsRoute
import io.ktor.server.application.*
import io.ktor.server.plugins.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing : Boolean = false) {
    install(ContentNegotiation) {}
    configureRouting()
    getInstrumentsRoute()
}

