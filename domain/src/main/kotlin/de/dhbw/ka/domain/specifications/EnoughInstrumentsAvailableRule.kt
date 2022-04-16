package de.dhbw.ka.domain.specifications

import de.dhbw.ka.domain.valueobjects.RentalRequest

object EnoughInstrumentsAvailableRule : RentalRule {
    override val denialMessage: String
        get() = "Unfortunately there are to Instruments at the moment to rent"

    override fun isSatisfiedBy(rentalRequest: RentalRequest): Boolean {
        return rentalRequest.rentalInstrument.quantity > 0
    }
}
