package de.dhbw.ka.rentalinstruments

import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

class GetQuantityOfRentalInstrument(private val rentalInstrumentRepository: RentalInstrumentRepository) {
    fun execute(instrumentIdentification: InstrumentIdentification) : Int {
        return rentalInstrumentRepository.checkAvailableQuantity(instrumentIdentification)
    }
}