package de.dhbw.ka.storage

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.dto.LentInstrumentDTO

interface LentInstrumentStorage {
    fun createLentingEntry(lentingEntryData: LentInstrumentDTO) : Boolean
    fun getAllInstrumentLentings() : List<LentInstrumentDTO>
    fun getLentInstrumentsByMember() : LentInstrument
}