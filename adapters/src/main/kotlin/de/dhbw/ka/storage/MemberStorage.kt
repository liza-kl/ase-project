package de.dhbw.ka.storage

import de.dhbw.ka.domain.entities.Member

interface MemberStorage {
    fun create(input: Member) : Boolean
    fun findById(id: Int) : Member?
    fun update(id: Int)
    fun findAll() : List<Member>
}