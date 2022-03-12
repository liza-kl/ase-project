package de.dhbw.ka.repository

import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.repository.InstrumentRentalRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.toRentalInstrument
import de.dhbw.ka.storage.InstrumentRentalStorage

class InstrumentRentalRepositoryImpl(private val instrumentRentalStorage: InstrumentRentalStorage) :
    InstrumentRentalRepository {
    override fun borrowInstrument(memberId: Int, instrumentToBeLent: InstrumentIdentification): Boolean {
        val rentalInstrumentDTO = RentalInstrumentDTO(
            memberId = memberId,
            instrumentIdentification = InstrumentIdentificationDTO(
                instrumentToBeLent.instrumentType,
                instrumentToBeLent.instrumentSerialNumber,
                instrumentToBeLent.instrumentManufacturer
            )
        )
        return instrumentRentalStorage.createRentalEntry(
            rentalInstrumentDTO
        )
    }

    override fun getLentInstrumentByMember(memberId: Int): InstrumentRentalEntry {
        return instrumentRentalStorage.getInstrumentsRentedByMember(
        )
    }

    override fun getAllRentalEntries(): List<InstrumentRentalEntry> {
        val result = instrumentRentalStorage.getAllInstrumentRentalEntries() // TODO Hier sind Ids noch richtig
        return result.map {
            toRentalInstrument(it)
        }
    }
}