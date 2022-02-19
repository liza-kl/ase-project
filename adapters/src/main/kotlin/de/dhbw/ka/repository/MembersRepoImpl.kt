package de.dhbw.ka.repository

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repositories.MemberRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class MembersRepoImpl() : MemberRepository {
    override fun create(input: Member) {
        transaction {
            MemberTable.insert {
                it[this.forename] = input.forename
                it[this.surname] = input.surname
                it[this.memberStatus] = input.memberStatus.toString()
            }
        }
    }

    override fun findById(id: Int): Member? {
        TODO("Not yet implemented")
    }

    override fun update(input: Member) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Member> {
        TODO("Not yet implemented")
    }

}

