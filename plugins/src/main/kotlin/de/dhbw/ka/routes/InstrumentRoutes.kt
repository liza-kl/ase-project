package de.dhbw.ka.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.getInstrumentsRoute() {
    routing {
        get("/instruments") {
            call.respondText { "Here you can see all the Instruments" }
        }
    }

}

