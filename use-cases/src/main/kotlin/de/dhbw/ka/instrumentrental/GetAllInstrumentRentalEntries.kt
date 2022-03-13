package de.dhbw.ka.instrumentrental

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository

class GetAllInstrumentRentalEntries(private val instrumentRentalEntryRepository: InstrumentRentalEntryRepository) {
    fun execute() : List<InstrumentRentalEntry> {
        return instrumentRentalEntryRepository.getAllRentalEntries()
    }
}