package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification


interface InstrumentRentalRepository {
    fun borrowInstrument(memberId: Int, instrumentToBeLent : InstrumentIdentification) : Boolean
    fun getLentInstrumentByMember(memberId: Int) : InstrumentRentalEntry
    fun getAllRentalEntries() : List<InstrumentRentalEntry>
}