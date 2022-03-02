package de.dhbw.ka.repository

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.valueobjects.MemberName
import de.dhbw.ka.domain.valueobjects.MemberStatus
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.toMemberDTO
import de.dhbw.ka.storage.MemberStorage

class MembersRepositoryImpl(private val memberStorage: MemberStorage) : MemberRepository {

    private fun toMember(input: MemberDTO): Member {
        return Member(
            id = input.id,
            memberName = MemberName(input.firstName, input.lastName),
            memberStatus = MemberStatus(input.memberStatus)
        )
    }

    override fun create(input: Member): Boolean {
        val memberDTO = toMemberDTO(input)
        val result = memberStorage.create(memberDTO);
        return result;
    }

    override fun findById(id: Int): Member? {
        TODO("Not yet implemented")
    }

    override fun update(input: Member): Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Member> {
        val result = memberStorage.findAll();
        return result.map { toMember(it) }
    }

}

