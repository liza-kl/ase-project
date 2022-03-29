package de.dhbw.ka.domain.valueobjects

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.entities.Member
import de.dhbw.ka.domain.exceptions.RentalInstrumentDoesntExist
import de.dhbw.ka.domain.exceptions.WrongMemberStatus
import de.dhbw.ka.domain.exceptions.WrongQuantity
import de.dhbw.ka.domain.specifications.MemberStatusSpecification
import de.dhbw.ka.domain.specifications.QuantitySpecification

data class RentalRequestApproval(
    var isRequestPermitted: Boolean = false,
    val member: Member,
    val rentalInstrumentExists: Boolean,
    val rentalInstrument: RentalInstrument
) {
    init {
        require(
            MemberStatusSpecification.isSatisfiedBy(member)
        ) {
            WrongMemberStatus().throwsException()
        }
        require(QuantitySpecification.isSatisfiedBy(rentalInstrument)) {
            WrongQuantity().throwsException()
        }
        require(rentalInstrumentExists) {
            RentalInstrumentDoesntExist().throwsException()
        }
        this.isRequestPermitted = true
    }
}
