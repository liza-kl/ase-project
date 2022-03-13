package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class FindMemberById(private val memberRepository: MemberRepository) {
    fun execute(memberId: Int): Member? {
        try {
            return memberRepository.findById(id = memberId)

        } catch (e: Exception) {
            throw Exception("Member with the id $memberId couldn't be found in the storage")
        }
    }
}