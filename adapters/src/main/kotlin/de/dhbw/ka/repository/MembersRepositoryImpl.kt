package de.dhbw.ka.repository

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import de.dhbw.ka.domain.valueobjects.MemberName
import de.dhbw.ka.domain.valueobjects.MemberStatus
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class MembersRepositoryImpl() : MemberRepository {

    override fun create(input: Member) : Boolean {
        transaction {
            MemberTable.insert {
              //  it[this.forename] = input.forename
              //  it[this.surname] = input.surname
                it[this.memberStatus] = input.memberStatus.toString()
            }
        }
        return true;
    }

    override fun findById(id: Int): Member? {
        TODO("Not yet implemented")
    }

    override fun update(input: Member) : Boolean {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Member> {
        return listOf(
            Member(1, MemberName("Max", "Mustermann"), MemberStatus(1)),
            Member(2, MemberName("Tanja", "Musterfrau"), MemberStatus(1)),
            Member(3, MemberName("Alina", "Mustermann"), MemberStatus(1))
        )
    }

}

