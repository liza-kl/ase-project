package de.dhbw.ka.storage

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.resultRowToMemberDTO
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2MemberStorage : MemberStorage {

    override fun create(input: MemberDTO) {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): MemberDTO? {
        TODO("Not yet implemented")
    }

    override fun update(id: Int) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<MemberDTO> {
        val membersList = mutableListOf<MemberDTO>()
        transaction {
            MemberTable.selectAll().map {
                val mappedResult = resultRowToMemberDTO(it)
                membersList.add(mappedResult)
            }
        }
        return membersList
    }
}