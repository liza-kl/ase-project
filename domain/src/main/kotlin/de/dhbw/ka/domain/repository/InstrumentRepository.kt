package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

interface InstrumentRepository {
    fun createInstrument(instrumentData: Instrument) : Boolean
    fun deleteInstrument(instrumentId: InstrumentIdentification) : Boolean
    fun getAllInstruments() : List<Instrument>
}
