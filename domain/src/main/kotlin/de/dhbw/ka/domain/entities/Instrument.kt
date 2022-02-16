package de.dhbw.ka.domain.entities

import de.dhbw.ka.domain.valueobjects.InstrumentAmbitus

class Instrument(val ambitus : InstrumentAmbitus, override val id: Int) : Entity {

}
