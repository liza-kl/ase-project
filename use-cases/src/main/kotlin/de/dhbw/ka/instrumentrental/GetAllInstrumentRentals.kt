package de.dhbw.ka.instrumentrental

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.repository.InstrumentRentalRepository

class GetAllInstrumentRentals(private val instrumentRentalRepository: InstrumentRentalRepository) {
    fun execute() : List<InstrumentRentalEntry> {
        return instrumentRentalRepository.getAllRentalEntries()
    }
}