package de.dhbw.ka.controllers

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMember
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMemberDTO
import de.dhbw.ka.members.CreateNewMember
import de.dhbw.ka.members.FindMemberById
import de.dhbw.ka.members.GetAllMembers
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.storage.h2.H2MemberStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMembers() {
    get("/members") {
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = H2MemberStorage())
        val getAllMembersUC = GetAllMembers(memberRepository = memberRepository)
        val members: List<Member> = getAllMembersUC.execute()
        val someList = mutableListOf<MemberDTO>()

        for (member in members) {
            val memberDTO = toMemberDTO(member)
            someList.add(memberDTO)
        }
        call.respond(someList)
    }
}

fun Route.getMemberById() {
    get("members/{id}") {
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = H2MemberStorage())
        val findMemberByIdUC = FindMemberById(memberRepository = memberRepository)
        val memberResult: Member? = call.parameters["id"]?.let { it1 -> findMemberByIdUC.execute(it1.toInt()) }
        if (memberResult != null) {
            call.respond(toMemberDTO(memberResult))

        }
    }

}

fun Route.addMember() {
    post("/members") {
        val receivedMemberParams = call.receive<MemberDTO>()
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = H2MemberStorage())
        val createNewMemberUC = CreateNewMember(memberRepository = memberRepository)
        createNewMemberUC.execute(toMember(receivedMemberParams))
        call.respondText(
            "Successfully created the member ${receivedMemberParams.firstName} ${receivedMemberParams.lastName} with the id ${receivedMemberParams.id}! ",
            status = HttpStatusCode.Created
        )
    }
}

fun Application.registerMemberController() {
    routing {
        getMembers()
        getMemberById()
        addMember()
    }
}

