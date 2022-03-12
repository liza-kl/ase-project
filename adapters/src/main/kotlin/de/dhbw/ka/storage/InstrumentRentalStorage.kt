package de.dhbw.ka.storage

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.dto.RentalInstrumentDTO

interface InstrumentRentalStorage {
    fun createRentalEntry(rentalEntryData: RentalInstrumentDTO) : Boolean
    fun getAllInstrumentRentalEntries() : List<RentalInstrumentDTO>
    fun getInstrumentsRentedByMember() : InstrumentRentalEntry
}