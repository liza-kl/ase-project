package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.valueobjects.InstrumentAmbitus
import de.dhbw.ka.domain.valueobjects.InstrumentCategory
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

data class Instrument(
    val instrumentIdentification: InstrumentIdentification,
    val instrumentAmbitus: InstrumentAmbitus,
    val instrumentCategory: InstrumentCategory,
)


