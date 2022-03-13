package de.dhbw.ka.storage

import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO

interface RentalInstrumentStorage {
    fun getAllRentalInstruments() : List<RentalInstrumentDTO>
    fun createRentalInstrument(rentalInstrumentData: RentalInstrumentDTO) : Boolean
    fun checkIfRentalInstrumentExists(instrumentIdentificationDTO: InstrumentIdentificationDTO) : Boolean
}