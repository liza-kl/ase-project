package de.dhbw.ka.domain.specifications

import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.valueobjects.MemberStatus

object MemberStatusSpecification : Specification<Member> {
    override fun isSatisfiedBy(condition: Member) : Boolean {
        return condition.memberStatus == MemberStatus("ACTIVE")
    }
}
