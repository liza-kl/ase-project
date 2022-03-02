package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.entities.Member

interface MemberRepository {
    fun create(input: Member) : Boolean
    fun findById(id: Int): Member?
    fun update(input: Member) : Boolean
    fun findAll(): List<Member>
}
