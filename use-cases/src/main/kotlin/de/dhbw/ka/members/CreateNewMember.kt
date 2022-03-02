package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class CreateNewMember(private val memberRepository: MemberRepository) {
    fun execute(memberData: Member) : Boolean {
        if(memberData.id == -1) {
            memberData.id = 60
        }
        return memberRepository.create(memberData)
    }
}