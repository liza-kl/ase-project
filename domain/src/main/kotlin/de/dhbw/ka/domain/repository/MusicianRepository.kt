package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.entities.Member

interface MusicianRepository {

    suspend fun getMembersOfMusicSociety() : Result<List<Member>>

    suspend fun addMember()

    suspend fun deleteMember()
}