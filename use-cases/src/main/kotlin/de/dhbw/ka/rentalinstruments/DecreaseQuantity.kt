package de.dhbw.ka.rentalinstruments

import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

class DecreaseQuantity(
    private val rentalInstrumentRepository: RentalInstrumentRepository,
) {
    fun execute(instrumentIdentification: InstrumentIdentification) {
        return rentalInstrumentRepository.decreaseQuantity(instrumentIdentification)
    }
}