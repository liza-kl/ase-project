package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.entities.Member

interface MemberRepository {
    fun create(input: Member) : Boolean
    fun findById(id: Int): Member?
    fun findAll(): List<Member>
}
