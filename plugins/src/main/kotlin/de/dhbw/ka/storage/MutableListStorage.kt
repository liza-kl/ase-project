package de.dhbw.ka.storage

import de.dhbw.ka.dto.MemberDTO

val memberStorage = mutableListOf<MemberDTO>(
    MemberDTO(1, "Celine", "Müller", "PASSIVE"),
    MemberDTO(2, "Shanti", "Müller", "ACTIVE"),
    MemberDTO(3, "Max", "Müller", "PASSIVE")
)

class MutableListStorage : MemberStorage {
    override fun create(input: MemberDTO) {
        memberStorage.add(input)
    }

    override fun findById(id: Int): MemberDTO? {
        return memberStorage.find { it.id == id }
    }

    override fun update(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<MemberDTO> {
        return memberStorage
    }
}