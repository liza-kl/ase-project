package de.dhbw.ka.dto

import de.dhbw.ka.datatables.MemberTable
import de.dhbw.ka.domain.entities.Member
import kotlinx.serialization.*
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class MemberDTO(val id: Int = -1, val firstName: String, val lastName: String, val memberStatus: Int = 0) {
    companion object MemberMapper {
        fun resultRowToMemberDTO(resultRow: ResultRow): MemberDTO {
            return MemberDTO(
                id = resultRow[MemberTable.id],
                firstName = resultRow[MemberTable.forename],
                lastName = resultRow[MemberTable.lastname],
                memberStatus = resultRow[MemberTable.memberStatus]
            )
        }

        fun toMemberDTO(input: Member): MemberDTO {
            return MemberDTO(
                id = input.id,
                firstName = input.memberName.firstName,
                lastName = input.memberName.lastName
            )
        }
    }
}
