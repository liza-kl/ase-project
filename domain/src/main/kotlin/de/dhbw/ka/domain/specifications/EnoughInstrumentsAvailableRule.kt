package de.dhbw.ka.domain.specifications

import de.dhbw.ka.domain.valueobjects.RentalRequest

object EnoughInstrumentsAvailableRule : RentalRule {
    override fun isSatisfiedBy(rentalRequest: RentalRequest): Boolean {
        return rentalRequest.rentalInstrument.quantity > 0
    }
}
