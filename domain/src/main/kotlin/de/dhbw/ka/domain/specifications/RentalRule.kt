package de.dhbw.ka.domain.specifications

import de.dhbw.ka.domain.valueobjects.RentalRequest

interface RentalRule {
    val denialMessage : String
    fun isSatisfiedBy(rentalRequest: RentalRequest) : Boolean
}
