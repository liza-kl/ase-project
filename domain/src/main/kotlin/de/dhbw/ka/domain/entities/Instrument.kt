package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.valueobjects.InstrumentAmbitus
import de.dhbw.ka.domain.valueobjects.InstrumentCategory

data class Instrument(
    val instrumentName: String,
    val ambitus: InstrumentAmbitus,
    val instrumentCategory: InstrumentCategory
) {

}


