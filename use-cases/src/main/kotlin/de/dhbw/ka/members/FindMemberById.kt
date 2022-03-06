package de.dhbw.ka.members

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository

class FindMemberById(private val memberRepository: MemberRepository) {
    fun execute(memberId: Int) : Member? {
        val checkIfMemberExists = CheckIfMemberExists(memberRepository)
        if (!checkIfMemberExists.execute(memberId)) {
            throw Exception("The member with the id $memberId doesn't exist in the database")
        }
        return memberRepository.findById(id = memberId)
    }
}