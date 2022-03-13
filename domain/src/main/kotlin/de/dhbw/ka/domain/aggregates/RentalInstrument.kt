package de.dhbw.ka.domain.aggregates

import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

data class RentalInstrument(
    val instrumentIdentification: InstrumentIdentification,
    val quantity: Int
) {
    init {
        require(quantity > 0) {
            throw IllegalArgumentException("Quantity can't be less than 1")
        }
    }
}
