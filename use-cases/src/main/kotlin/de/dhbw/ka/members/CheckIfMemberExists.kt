package de.dhbw.ka.members

import de.dhbw.ka.domain.repository.MemberRepository

class CheckIfMemberExists(private val memberRepository: MemberRepository) {
    fun execute(memberId: Int): Boolean {
        return memberRepository.findById(memberId) != null
    }
}