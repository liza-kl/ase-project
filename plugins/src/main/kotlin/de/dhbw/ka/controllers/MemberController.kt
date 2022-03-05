package de.dhbw.ka.controllers

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMember
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMemberDTO
import de.dhbw.ka.members.CreateNewMember
import de.dhbw.ka.members.GetAllMembers
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.storage.MutableListStorage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.getMembers() {
    get("/members") {
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = MutableListStorage())
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

}

fun Route.borrowInstrument() {

}

fun Route.addMember() {
    post("/members") {
        val receivedMemberParams = call.receive<MemberDTO>()
        val memberRepository: MemberRepository = MembersRepositoryImpl(memberStorage = MutableListStorage())
        val createNewMemberUC = CreateNewMember(memberRepository = memberRepository)
        createNewMemberUC.execute(toMember(receivedMemberParams))
        call.respondText("Successfully created a member! $receivedMemberParams", status = HttpStatusCode.Created)
    }
}

fun Application.registerMemberController() {
    routing {
        getMembers()
        addMember()
    }
}

