package de.dhbw.ka.storage.local

import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.storage.MemberStorage

val memberStorage = mutableListOf<MemberDTO>(
    MemberDTO(1, "Celine", "Müller", "PASSIVE"),
    MemberDTO(2, "Shanti", "Müller", "ACTIVE"),
    MemberDTO(3, "Max", "Müller", "PASSIVE")
)

class MutableListStorage : MemberStorage {
    override fun create(input: MemberDTO): Boolean {
        memberStorage.add(input)
        return true
    }

    override fun findById(memberId: Int): MemberDTO? {
        return memberStorage.find { it.id == memberId }
    }

    override fun update(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<MemberDTO> {
        return memberStorage
    }
}