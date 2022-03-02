package de.dhbw.ka.dto

import de.dhbw.ka.domain.valueobjects.MemberStatus
import kotlinx.serialization.Serializable

@Serializable
data class MemberStatusDTO(private val memberStatus: String) {
    companion object MemberStatusMapper {

        fun toMemberStatus(memberStatusInput: String): MemberStatus {
            return MemberStatus(status = memberStatusInput)
        }

        fun <Type>toMemberStatusDTO(input: Type): MemberStatusDTO {
            return MemberStatusDTO(
                memberStatus = input.toString()
            )
        }
    }
}
