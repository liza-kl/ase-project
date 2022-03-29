package de.dhbw.ka.repository

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMember
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMemberDTO
import de.dhbw.ka.storage.interfaces.MemberStorage

class MembersRepositoryImpl(private val memberStorage: MemberStorage) : MemberRepository {

    override fun create(input: Member): Boolean {
        val memberDTO = toMemberDTO(input)
        return memberStorage.create(memberDTO)
    }

    override fun findById(id: Int): Member? {
        val result = memberStorage.findById(id)
        return result?.let { toMember(it) }
    }

    override fun findAll(): List<Member> {
        val result = memberStorage.findAll()
        return result.map { toMember(it) }
    }

}

