package de.dhbw.ka

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            call.respondText("Musikvereinsverwaltung")
        }
    }
}
