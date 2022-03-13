package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification


interface InstrumentRentalEntryRepository {
    fun rentInstrument(memberId: Int, instrumentIdentification: InstrumentIdentification) : Boolean
    fun getAllRentalEntries() : List<InstrumentRentalEntry>
}