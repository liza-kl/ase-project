package de.dhbw.ka.repository

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.repository.LentInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.LentInstrumentDTO
import de.dhbw.ka.storage.LentInstrumentStorage

class LentInstrumentRepositoryImpl(private val lentInstrumentStorage: LentInstrumentStorage) :
    LentInstrumentRepository {
    override fun borrowInstrument(memberId: Int, instrumentToBeLent: InstrumentIdentification): Boolean {
        return lentInstrumentStorage.createLentingEntry(
            lentingEntryData = LentInstrumentDTO(
                memberId = memberId,
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentToBeLent.instrumentType,
                    instrumentToBeLent.instrumentSerialNumber,
                    instrumentToBeLent.instrumentManufacturer
                )
            )
        )
    }

    override fun getLentInstrumentByMember(memberId: Int): LentInstrument {
        return lentInstrumentStorage.getLentInstrumentsByMember(

        )
    }
}