package de.dhbw.ka.domain.aggregates

import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

data class LentInstrument(
    val memberId: Int,
    val instrumentIdentification: InstrumentIdentification
)