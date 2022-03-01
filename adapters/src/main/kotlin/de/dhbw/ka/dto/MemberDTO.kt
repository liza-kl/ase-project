package de.dhbw.ka.dto

@kotlinx.serialization.Serializable
data class MemberDTO(val id: Int = -1, val firstName: String, val lastName: String, val memberStatus: Int = 0) {
}