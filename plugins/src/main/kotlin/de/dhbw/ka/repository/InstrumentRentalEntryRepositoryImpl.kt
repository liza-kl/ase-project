package de.dhbw.ka.repository

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.repository.InstrumentRentalEntryRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentEntryDTO
import de.dhbw.ka.dto.RentalInstrumentEntryDTO.RentalInstrumentEntryMapper.toRentalInstrumentEntry
import de.dhbw.ka.storage.InstrumentRentalEntryStorage

class InstrumentRentalEntryRepositoryImpl(private val instrumentRentalEntryStorage: InstrumentRentalEntryStorage) :
    InstrumentRentalEntryRepository {
    override fun rentInstrument(memberId: Int, instrumentIdentification: InstrumentIdentification): Boolean {
        val rentalInstrumentEntryDTO = RentalInstrumentEntryDTO(
            memberId = memberId,
            instrumentIdentification = InstrumentIdentificationDTO(
                instrumentIdentification.instrumentManufacturer,
                instrumentIdentification.instrumentSerialNumber,
                instrumentIdentification.instrumentType,
            )
        )
        return instrumentRentalEntryStorage.createRentalEntry(
            rentalInstrumentEntryDTO
        )
    }

    override fun getAllRentalEntries(): List<InstrumentRentalEntry> {
        val result = instrumentRentalEntryStorage.getAllInstrumentRentalEntries()
        return result.map {
            toRentalInstrumentEntry(it)
        }
    }
}