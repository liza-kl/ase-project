package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.valueobjects.InstrumentAmbitus

class Instrument(val instrumentName: String, val ambitus : InstrumentAmbitus, override val id: Int) : Entity {

}

enum class InstrumentCategory {
    WOODWIND,
    BRASS,
    PERCUSSION,
    KEYBOARD,
    STRING,
    GUITAR
}
