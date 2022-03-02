package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.valueobjects.MemberName
import de.dhbw.ka.domain.valueobjects.MemberStatus

data class Member(
    var id: Int,
    val memberName : MemberName,
    val memberStatus: MemberStatus
    )


