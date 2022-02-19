package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.valueobjects.MemberStatus

data class Member(
    val id: Int,
    val forename: String,
    val surname: String,
    val memberStatus: MemberStatus
    )


