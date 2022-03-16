package de.dhbw.ka.server

import de.dhbw.ka.configureRouting
import de.dhbw.ka.controllers.registerInstrumentController
import de.dhbw.ka.controllers.registerMemberController
import de.dhbw.ka.controllers.registerRentalInstrumentEntriesController
import de.dhbw.ka.controllers.registerRentalInstrumentsController
import de.dhbw.ka.storage.h2.DatabaseFactory
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.serialization.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module(testing: Boolean = true) {
    install(DoubleReceive) {
        receiveEntireContent = true
    }
    install(CORS) {
        anyHost()
    }
    install(ContentNegotiation) {
        json()
    }
    DatabaseFactory.init()

    configureRouting()
    registerMemberController()
    registerInstrumentController()
    registerRentalInstrumentsController()
    registerRentalInstrumentEntriesController()
}



