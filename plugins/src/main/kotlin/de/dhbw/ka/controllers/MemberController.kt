package de.dhbw.ka.controllers

import de.dhbw.ka.domain.valueobjects.toMemberStatus
import de.dhbw.ka.dto.CreateMember
import de.dhbw.ka.repository.MembersRepoImpl
import de.dhbw.ka.services.MemberService
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMembers() {
        get("/members") {
          call.respondText { "" }
        }
}

fun Route.addMember() {
    post("/members") {
        val data = call.receiveParameters().toCreateMember()
        val repo = MemberService(MembersRepoImpl()).create(data)
        call.respond(HttpStatusCode.OK, repo)
    }
}
private fun Parameters.toCreateMember(): CreateMember {
    return CreateMember(
        forename = this["forename"]!!,
        surname = this["surname"]!!,
        memberStatus = this["memberStatus"]!!.toMemberStatus()
    )
}

fun Application.registerMemberController() {
    routing {
        getMembers()
        addMember()
    }
}
