package de.dhbw.ka.rentalinstruments

import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

class CheckIfRentalInstrumentExists(private val rentalInstrumentRepository: RentalInstrumentRepository) {
    fun execute(rentalInstrumentToCheck: InstrumentIdentification) : Boolean {
        if (rentalInstrumentRepository.checkIfRentalInstrumentExists(rentalInstrumentToCheck)) {
            throw Exception("Rental Instrument already exists!")
        }
        return false
    }
}