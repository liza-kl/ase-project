package de.dhbw.ka.domain.specifications

import de.dhbw.ka.domain.valueobjects.MemberStatus
import de.dhbw.ka.domain.valueobjects.RentalRequest

object MemberIsActiveRule : RentalRule {
    override val denialMessage: String
        get() = "Member has the wrong status"

    override fun isSatisfiedBy(rentalRequest: RentalRequest) : Boolean {
        return rentalRequest.member.memberStatus == MemberStatus("ACTIVE")
    }
}
