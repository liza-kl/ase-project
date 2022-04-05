package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

interface RentalInstrumentRepository {
    fun createRentalInstrument(rentalInstrument: RentalInstrument) : Boolean
    fun getRentalInstruments() : List<RentalInstrument>
    fun checkIfRentalInstrumentExists(instrumentIdentification: InstrumentIdentification) : Boolean
    fun checkAvailableQuantity(instrumentIdentification: InstrumentIdentification) : Int
    fun getRentalInstrumentByIdentification(instrumentIdentification: InstrumentIdentification) : RentalInstrument?
    fun decreaseQuantity(instrumentIdentification: InstrumentIdentification)
}
