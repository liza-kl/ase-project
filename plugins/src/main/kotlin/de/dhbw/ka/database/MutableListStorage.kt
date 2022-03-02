package de.dhbw.ka.database

import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.storage.MemberStorage

class MutableListStorage : MemberStorage {

    private val memberStorage = mutableListOf<MemberDTO>(
        MemberDTO(1,"Celine","Müller",1),
        MemberDTO(2,"Shanti","Müller",1),
        MemberDTO(3,"Max","Müller",1)
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