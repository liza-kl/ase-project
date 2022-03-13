package de.dhbw.ka.repository

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.toInstrument
import de.dhbw.ka.dto.InstrumentDTO.InstrumentMapper.toInstrumentDTO
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.storage.InstrumentStorage

class InstrumentRepositoryImpl(private val instrumentStorage: InstrumentStorage) : InstrumentRepository {
    override fun createInstrument(instrumentData: Instrument): Boolean {
        val instrumentDTO = toInstrumentDTO(instrumentData)
        return instrumentStorage.create(instrumentDTO)
    }

    override fun deleteInstrument(instrumentId: InstrumentIdentification): Boolean {
        TODO("Not yet implemented")
    }

    override fun getAllInstruments(): List<Instrument> {
        val result = instrumentStorage.getAllInstruments()
        return result.map { toInstrument(it) }
    }

    override fun checkIfInstrumentExists(instrumentIdentification: InstrumentIdentification): Boolean {
        val instrumentIdentificationDTO = InstrumentIdentificationDTO(
            instrumentIdentification.instrumentManufacturer,
            instrumentIdentification.instrumentSerialNumber,
            instrumentType = instrumentIdentification.instrumentType)
        return instrumentStorage.checkIfInstrumentExists(instrumentIdentificationDTO)
    }
}