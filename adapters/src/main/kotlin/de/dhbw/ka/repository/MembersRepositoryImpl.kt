package de.dhbw.ka.repository

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repository.MemberRepository
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.statements.DeleteStatement.Companion.all
import org.jetbrains.exposed.sql.transactions.transaction

// In der Implementierung soll nur Daten da sein, keine Logik.
class MembersRepositoryImpl(private val table : MemberTable) : MemberRepository {

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
        return MemberTable.selectAll().map {it -> it.toString}
    }

}

