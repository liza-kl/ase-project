package de.dhbw.ka.storage

import de.dhbw.ka.dto.MemberDTO

class MutableListStorage : MemberStorage {

    private val memberStorage = mutableListOf<MemberDTO>(
        MemberDTO(1,"Celine","Müller","OINK"),
        MemberDTO(2,"Shanti","Müller","ACTIVE"),
        MemberDTO(3,"Max","Müller","PASSIVE")
    )

    override fun create(input: MemberDTO): Boolean {
        memberStorage.add(input);
        return true;
    }

    override fun findById(id: Int): MemberDTO? {
        return memberStorage.find { it.id == id };
    }

    override fun update(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll() : List<MemberDTO> {
        return memberStorage;
    }
}