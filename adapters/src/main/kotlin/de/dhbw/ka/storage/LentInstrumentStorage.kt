package de.dhbw.ka.storage

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.dto.LentInstrumentDTO

interface LentInstrumentStorage {
    fun createRentalEntry(lentingEntryData: LentInstrumentDTO) : Boolean
    fun getAllLentInstruments() : List<LentInstrumentDTO>
    fun getLentInstrumentsByMember() : LentInstrument
}