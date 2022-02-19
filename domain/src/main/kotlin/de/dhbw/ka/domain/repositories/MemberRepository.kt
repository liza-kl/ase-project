package de.dhbw.ka.domain.repositories

import de.dhbw.ka.domain.entities.Member

interface MemberRepository {
    fun create(input: Member)
    fun findById(id: Int): Member?
    fun update(input: Member)
    fun findAll(): List<Member>
}
