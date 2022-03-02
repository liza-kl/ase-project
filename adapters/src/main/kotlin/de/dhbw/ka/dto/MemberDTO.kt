package de.dhbw.ka.dto

import de.dhbw.ka.datatables.MemberTable

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.valueobjects.MemberName
import de.dhbw.ka.domain.valueobjects.MemberStatus
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class MemberDTO(val id: Int = -1, val firstName: String, val lastName: String, val memberStatus: String) {
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
                lastName = input.memberName.lastName,
                memberStatus = input.memberStatus.status
            )
        }

        fun toMember(input: MemberDTO): Member {
            return Member(
                id = input.id,
                memberName = MemberName(input.firstName, input.lastName),
                memberStatus = MemberStatus(input.memberStatus)
            )
        }
    }
}
