package de.dhbw.ka.server

import de.dhbw.ka.configureRouting
import de.dhbw.ka.controllers.registerInstrumentController
import de.dhbw.ka.controllers.registerMemberController
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing : Boolean = false) {
    install(CORS) {
        anyHost()
    }
    install(ContentNegotiation) {
        json()
    }
    configureRouting()
    registerMemberController()
    registerInstrumentController()
}

