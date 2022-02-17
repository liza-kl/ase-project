package de.dhbw.ka.controllers

import de.dhbw.ka.database.memberStorage
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.repository.MembersRepoImpl
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMembers() {
        get("/members") {
            if (memberStorage.isNotEmpty()) {
                call.respond(memberStorage)
            } else {
                call.respondText("Unfortunately no members found", status = HttpStatusCode.NotFound)
            }
        }
}

fun Route.addMember() {
    post("/members") {
        val databla = MembersRepoImpl(memberStorage);
        val test = call.receive<Member>();
        databla.addMember(test)
        call.respondText("Member stored correctly", status = HttpStatusCode.Created)
    }
}


fun Application.registerMemberController() {
    routing {
        getMembers()
        addMember()
    }
}
