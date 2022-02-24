package de.dhbw.ka.controllers

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.members.GetAllMembers
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMembers() {
        get("/members") {
        val members : List<Member> = GetAllMembers().execute();
          call.respondText { members.toString()  }
        }
}

fun Route.addMember() {
    post("/members") {
        call.respond(HttpStatusCode.OK, "")
    }
}

fun Application.registerMemberController() {
    routing {
        getMembers()
        addMember()
    }
}
