package de.dhbw.ka.repository

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.storage.MemberStorage

// In der Implementierung soll nur Daten da sein, keine Logik.
class MembersRepositoryImpl(private val memberStorage: MemberStorage) : MemberRepository {

    override fun create(input: Member) : Boolean {
        val result = memberStorage.create(input);
        return result;
    }

    override fun findById(id: Int): Member? {
        TODO("Not yet implemented")
    }

    override fun update(input: Member) : Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Member> {
        return memberStorage.findAll();
    }

}

