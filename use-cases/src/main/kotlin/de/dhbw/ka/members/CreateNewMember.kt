package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class CreateNewMember(private val memberRepository: MemberRepository) {
    fun execute(memberData: Member) : Boolean {
        return memberRepository.create(memberData)
    }
}
