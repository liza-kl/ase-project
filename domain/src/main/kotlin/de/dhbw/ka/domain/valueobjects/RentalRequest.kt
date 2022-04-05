package de.dhbw.ka.domain.valueobjects

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.entities.Member

data class RentalRequest(
    val member: Member,
    val rentalInstrument: RentalInstrument
)
