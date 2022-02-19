package de.dhbw.ka.dto

import de.dhbw.ka.domain.valueobjects.MemberStatus

data class CreateMember(
    val forename: String,
    val surname: String,
    val memberStatus: MemberStatus
)
