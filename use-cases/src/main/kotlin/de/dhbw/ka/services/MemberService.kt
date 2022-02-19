package de.dhbw.ka.services

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repositories.MemberRepository
import de.dhbw.ka.dto.CreateMember

class MemberService(private val memberRepository: MemberRepository) {
    fun create(input: CreateMember) {
        val data = Member(id = 0, forename = input.forename, surname = input.surname, memberStatus = input.memberStatus)
        memberRepository.create(data)
    }

}
