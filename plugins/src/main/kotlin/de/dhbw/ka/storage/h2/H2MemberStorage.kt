package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.dto.MemberDTO
import de.dhbw.ka.dto.MemberDTO.MemberMapper.resultRowToMemberDTO
import de.dhbw.ka.storage.MemberStorage
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2MemberStorage : MemberStorage {

    override fun create(input: MemberDTO): Boolean {
        transaction {
            MemberTable.insert {
                it[firstName] = input.firstName
                it[lastName] = input.lastName
                it[memberStatus] = input.memberStatus
            }
        }
        return true
    }

    override fun findById(memberId: Int): MemberDTO? {
        val result = transaction {
            MemberTable.select(MemberTable.id eq memberId).single() //TODO is single() the correct method?
        }
       return resultRowToMemberDTO(result)
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