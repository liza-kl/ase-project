package de.dhbw.ka.controllers

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.members.GetAllMembers
import de.dhbw.ka.repository.MembersRepositoryImpl
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMembers() {
        get("/members") {
        val memberRepository: MemberRepository = MembersRepositoryImpl(MemberTable)
        val getAllMembersUC = GetAllMembers(memberRepository = memberRepository);
        val members : List<Member> = getAllMembersUC.execute();
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
