package de.dhbw.ka.controllers

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

import io.ktor.server.request.*

fun Application.getInstrumentsRoute() {
    routing {
        get("/instruments") {
            call.respondText {
                "bla"
            }
        }
        post {
          //  val customer = call.receive<MemberDTO>()

        }
    }

}

