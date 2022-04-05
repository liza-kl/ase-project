package de.dhbw.ka.repository

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrument
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrumentDTO
import de.dhbw.ka.storage.interfaces.RentalInstrumentStorage

class RentalInstrumentRepositoryImpl(private val rentalInstrumentStorage: RentalInstrumentStorage) :
    RentalInstrumentRepository {

    override fun createRentalInstrument(rentalInstrument: RentalInstrument): Boolean {
        val rentalInstrumentDTO: RentalInstrumentDTO = toRentalInstrumentDTO(rentalInstrument)
        return rentalInstrumentStorage.createRentalInstrument(rentalInstrumentDTO)
    }

    override fun getRentalInstruments(): List<RentalInstrument> {
        val result = rentalInstrumentStorage.getAllRentalInstruments()
        return result.map { toRentalInstrument(it) }
    }

    override fun checkIfRentalInstrumentExists(instrumentIdentification: InstrumentIdentification): Boolean {
        val instrumentIdentificationDTO = InstrumentIdentificationDTO(
            instrumentManufacturer = instrumentIdentification.instrumentManufacturer,
            instrumentSerialNumber = instrumentIdentification.instrumentSerialNumber,
            instrumentType = instrumentIdentification.instrumentType
        )
        return rentalInstrumentStorage.checkIfRentalInstrumentExists(instrumentIdentificationDTO)

    }

    override fun checkAvailableQuantity(instrumentIdentification: InstrumentIdentification): Int {
        val instrumentIdentificationDTO = InstrumentIdentificationDTO(
            instrumentIdentification.instrumentManufacturer,
            instrumentIdentification.instrumentSerialNumber,
            instrumentType = instrumentIdentification.instrumentType
        )
        return rentalInstrumentStorage.checkAvailableQuantity(instrumentIdentificationDTO)
    }

    override fun getRentalInstrumentByIdentification(instrumentIdentification: InstrumentIdentification): RentalInstrument? {
        val result = rentalInstrumentStorage.getRentalInstrumentByIdentification(instrumentIdentification)
        return result?.let { toRentalInstrument(it) }
    }

    override fun decreaseQuantity(instrumentIdentification: InstrumentIdentification) {
        val instrumentIdentificationDTO = InstrumentIdentificationDTO(
            instrumentManufacturer = instrumentIdentification.instrumentManufacturer,
            instrumentSerialNumber = instrumentIdentification.instrumentSerialNumber,
            instrumentType = instrumentIdentification.instrumentType
        )
        return rentalInstrumentStorage.decreaseQuantity(instrumentIdentificationDTO)
    }

}
