package de.dhbw.ka.storage

import de.dhbw.ka.dto.MemberDTO

interface MemberStorage {
    fun create(input: MemberDTO) : Boolean
    fun findById(id: Int) : MemberDTO?
    fun update(id: Int)
    fun findAll() : List<MemberDTO>
}