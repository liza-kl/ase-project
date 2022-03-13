package de.dhbw.ka.rentalinstruments

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.RentalInstrumentRepository

class GetAllRentalInstruments(private val rentalInstrumentRepository: RentalInstrumentRepository) {
    fun execute() : List<RentalInstrument> {
        return rentalInstrumentRepository.getRentalInstruments()
    }
}