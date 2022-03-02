package de.dhbw.ka.controllers

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getInstrumentsRoute() {
    get("/instruments") {
        call.respondText("Instruments")
    }
}

fun Application.registerInstrumentController() {
    routing {
        getInstrumentsRoute()
    }
}

