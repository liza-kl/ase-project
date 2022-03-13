package de.dhbw.ka.rentalinstruments

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.instruments.CheckIfInstrumentExists

class CreateRentalInstrument(
    private val rentalInstrumentRepository: RentalInstrumentRepository,
    private val instrumentRepository: InstrumentRepository
) {

    fun execute(rentalInstrument: RentalInstrument): Boolean {
        val doesInstrumentExist = CheckIfInstrumentExists(instrumentRepository)
        val doesRentalInstrumentExists = CheckIfRentalInstrumentExists(rentalInstrumentRepository)
        if (!doesInstrumentExist.execute(rentalInstrument.instrumentIdentification)) {
            throw Exception("Instrument must be present in the Instrument database in order to create a" +
                    "Rental instrument")
        }
        if (doesRentalInstrumentExists.execute(rentalInstrument.instrumentIdentification)) {
            throw Exception("The same Rental Instrument can't be created twice")
        }
        if (rentalInstrument.quantity <= 0) {
            throw Exception("You need to have at least 1 rental instrument in your Rental Instrument storage!")
        }
        return rentalInstrumentRepository.createRentalInstrument(rentalInstrument)
    }
}