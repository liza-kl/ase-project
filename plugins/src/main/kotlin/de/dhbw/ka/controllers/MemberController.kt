package de.dhbw.ka.controllers

import de.dhbw.ka.storage.MutableListStorage
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberStatusDTO
import de.dhbw.ka.members.GetAllMembers
import de.dhbw.ka.repository.MembersRepositoryImpl
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*


private fun toMemberDTO(input: Member): MemberDTO {
    return MemberDTO(
        id = input.id,
        firstName = input.memberName.firstName,
        lastName = input.memberName.lastName,
        memberStatus = input.memberStatus.toString())
}

fun Route.getMembers() {
    get("/members") {
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = MutableListStorage())
        val getAllMembersUC = GetAllMembers(memberRepository = memberRepository);
        val members: List<Member> = getAllMembersUC.execute();
        val someList = mutableListOf<MemberDTO>()

        for (member in members) {
            val memberDTO = toMemberDTO(member)
            someList.add(memberDTO)
        }
        call.respond(someList)
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

