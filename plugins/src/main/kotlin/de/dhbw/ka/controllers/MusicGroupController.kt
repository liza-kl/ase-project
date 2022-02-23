package de.dhbw.ka.controllers

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMusicGroups() {
    get("/musicgroup") {
        call.respondText { "" }
    }
}

fun Route.addMusicGroup() {
    post("/musicgroup") {
        call.respond(HttpStatusCode.OK, "")
    }
}

fun Application.registerMusicGroupController() {
    routing {
        getMusicGroups()
        addMusicGroup()
    }
}
