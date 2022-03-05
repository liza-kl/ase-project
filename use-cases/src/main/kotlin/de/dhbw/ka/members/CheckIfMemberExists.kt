package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class CheckIfMemberExists(private val memberRepository: MemberRepository) {
    fun execute(memberData: Member): Boolean {
        if (memberData.memberId?.let { memberRepository.findById(it) } != null) {
            return true
        }
        return false;
    }
}