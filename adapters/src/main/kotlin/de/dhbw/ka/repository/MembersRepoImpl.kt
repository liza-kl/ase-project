package de.dhbw.ka.repository

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.repositories.MemberRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection

class MembersRepoImpl(private val remoteDataSource: MutableList<Member>) : MemberRepository {

    override suspend fun getMembersOfMusicSociety(): List<Member> {
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
        return transaction {
            return@transaction MemberTable.selectAll().map { toMember(it) }
        }
    }

    override suspend fun addMember(member: Member) {
        TransactionManager.manager.defaultIsolationLevel = Connection.TRANSACTION_SERIALIZABLE
        return transaction {
            MemberTable.insert {
                it[name] = member.name
                it[status] = member.memberStatus
            }
        }
    }

    override suspend fun deleteMember(memberId: Int) {
        MemberTable.deleteWhere { MemberTable.id eq memberId }
    }

    private fun toMember(row: ResultRow): Member =
        Member(
            id = row[MemberTable.id],
            name = row[MemberTable.name],
            memberStatus = row[MemberTable.status]
        )
}

