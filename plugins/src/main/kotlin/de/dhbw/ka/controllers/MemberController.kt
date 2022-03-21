package de.dhbw.ka.controllers

import de.dhbw.ka.controllers.MemberControllerProperties.memberRepository
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMember
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMemberDTO
import de.dhbw.ka.members.CreateNewMember
import de.dhbw.ka.members.FindMemberById
import de.dhbw.ka.members.GetAllMembers
import de.dhbw.ka.repository.MembersRepositoryImpl
import de.dhbw.ka.storage.factories.StandardMemberStorageFactory
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*

internal object MemberControllerProperties {
    private val memberStorageFactory = StandardMemberStorageFactory()
    private val memberStorage = memberStorageFactory.createMemberStorageFromType("h2")
    val memberRepository: MemberRepository = MembersRepositoryImpl(
        memberStorage = memberStorage
    )
}

fun Route.getMembers() {
    get("/members") {
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
        val createNewMemberUC = CreateNewMember(memberRepository = memberRepository)
        try {
            createNewMemberUC.execute(toMember(receivedMemberParams))
            call.respondText(
                "Successfully created the member ${receivedMemberParams.firstName} ${receivedMemberParams.lastName} with the id ${receivedMemberParams.id}! ",
                status = HttpStatusCode.OK
            )
        } catch(e : Exception ) {
            call.respondText(
                "Unfortunately something went wrong ${e.message}",
                status = HttpStatusCode.NotFound
            )
        }

    }
}

fun Application.registerMemberController() {
    routing {
        getMembers()
        getMemberById()
        addMember()
    }
}

