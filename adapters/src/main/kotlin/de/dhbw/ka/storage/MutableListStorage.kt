package de.dhbw.ka.storage

import de.dhbw.ka.domain.entities.Member

class MutableListStorage : MemberStorage {

    private val memberStorage = mutableListOf<Member>()

    override fun create(input: Member): Boolean {
        memberStorage.add(input);
        return true;
    }

    override fun findById(id: Int) : Member? {
        return memberStorage.find { it.id == id}
    }

    override fun update(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll() : List<Member> {
        return memberStorage;
    }
}