package de.dhbw.ka.storage.interfaces

import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO

interface RentalInstrumentStorage {
    fun getAllRentalInstruments() : List<RentalInstrumentDTO>
    fun createRentalInstrument(rentalInstrumentData: RentalInstrumentDTO) : Boolean
    fun checkIfRentalInstrumentExists(instrumentIdentificationDTO: InstrumentIdentificationDTO) : Boolean
    fun checkAvailableQuantity(instrumentIdentificationDTO: InstrumentIdentificationDTO) : Int
    fun decreaseQuantity(instrumentIdentificationDTO: InstrumentIdentificationDTO)
    fun getRentalInstrumentByIdentification(instrumentIdentificationDTO: InstrumentIdentification) : RentalInstrumentDTO?
}
