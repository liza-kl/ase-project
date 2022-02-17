package de.dhbw.ka.services

import de.dhbw.ka.domain.repositories.MemberRepository

public class GetMembersUseCase(private val membersRepository: MemberRepository) {
    suspend operator fun invoke()  = membersRepository.getMembersOfMusicSociety()
}
