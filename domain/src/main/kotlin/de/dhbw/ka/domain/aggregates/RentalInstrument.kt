package de.dhbw.ka.domain.aggregates

import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

data class RentalInstrument(
    val rentalInstrument: InstrumentIdentification,
    val quantity: Int
)
