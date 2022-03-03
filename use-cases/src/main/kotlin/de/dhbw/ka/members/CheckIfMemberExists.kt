package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class CheckIfMemberExists(private val memberRepository: MemberRepository) {
    fun execute(memberData: Member): Boolean {
        if (memberRepository.findById(memberData.id) != null) {
            return true
        }
        return false;
    }
}