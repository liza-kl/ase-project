package de.dhbw.ka.storage

import de.dhbw.ka.dto.MemberDTO

interface MemberStorage {
    fun create(input: MemberDTO) : Boolean
    fun findById(memberId: Int) : MemberDTO?
    fun findAll() : List<MemberDTO>
}