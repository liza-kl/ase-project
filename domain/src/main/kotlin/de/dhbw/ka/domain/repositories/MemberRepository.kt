package de.dhbw.ka.domain.repositories

import de.dhbw.ka.domain.entities.Member

interface MemberRepository {

    suspend fun getMembersOfMusicSociety() : Result<List<Member>>

    suspend fun addMember(member: Member)

    suspend fun deleteMember(memberId: Int)

}
