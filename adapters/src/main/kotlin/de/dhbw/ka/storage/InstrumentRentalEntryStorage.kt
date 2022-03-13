package de.dhbw.ka.storage

import de.dhbw.ka.dto.RentalInstrumentEntryDTO

interface InstrumentRentalEntryStorage {
    fun createRentalEntry(rentalEntryData: RentalInstrumentEntryDTO): Boolean
    fun getAllInstrumentRentalEntries(): List<RentalInstrumentEntryDTO>
}