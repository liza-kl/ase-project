package de.dhbw.ka.database

import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.storage.MemberStorage

class MutableListStorage : MemberStorage {

    private val memberStorage = mutableListOf<MemberDTO>()

    override fun create(input: MemberDTO): Boolean {
        memberStorage.add(input);
        return true;
    }

    override fun findById(id: Int) : MemberDTO? {
        val result = memberStorage.find { it.id == id}
        return result;
    }

    override fun update(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll() : List<MemberDTO> {
        return memberStorage;
    }
}