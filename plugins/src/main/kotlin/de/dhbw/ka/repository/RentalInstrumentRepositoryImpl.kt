package de.dhbw.ka.repository

import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.repository.RentalInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrument
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrumentDTO
import de.dhbw.ka.storage.RentalInstrumentStorage

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
            instrumentIdentification.instrumentManufacturer,
            instrumentIdentification.instrumentSerialNumber,
            instrumentType = instrumentIdentification.instrumentType)
        return rentalInstrumentStorage.checkIfRentalInstrumentExists(instrumentIdentificationDTO)

    }

}