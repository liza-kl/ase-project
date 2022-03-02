package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class GetAllMembers(private val memberRepository: MemberRepository) {

    fun execute(): List<Member> {
        return memberRepository.findAll()
    }
}
