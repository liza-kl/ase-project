package de.dhbw.ka.domain.aggregates

import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

data class InstrumentRentalEntry(
    val rentalEntryId: Int,
    val memberId: Int,
    val instrumentIdentification: InstrumentIdentification
)