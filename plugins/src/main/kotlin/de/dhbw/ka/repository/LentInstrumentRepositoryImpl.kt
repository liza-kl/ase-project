package de.dhbw.ka.repository

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.repository.LentInstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.LentInstrumentDTO
import de.dhbw.ka.dto.LentInstrumentDTO.LentInstrumentMapper.toLentInstrument
import de.dhbw.ka.storage.LentInstrumentStorage

class LentInstrumentRepositoryImpl(private val lentInstrumentStorage: LentInstrumentStorage) :
    LentInstrumentRepository {
    override fun borrowInstrument(memberId: Int, instrumentToBeLent: InstrumentIdentification): Boolean {
        val lentInstrumentDTO = LentInstrumentDTO(
            memberId = memberId,
            instrumentIdentification = InstrumentIdentificationDTO(
                instrumentToBeLent.instrumentType,
                instrumentToBeLent.instrumentSerialNumber,
                instrumentToBeLent.instrumentManufacturer
            )
        )
        return lentInstrumentStorage.createRentalEntry(
            lentInstrumentDTO
        )
    }

    override fun getLentInstrumentByMember(memberId: Int): LentInstrument {
        return lentInstrumentStorage.getLentInstrumentsByMember(

        )
    }

    override fun getAllLentInstruments(): List<LentInstrument> {
        val result = lentInstrumentStorage.getAllLentInstruments()
        return result.map {
            toLentInstrument(it)
        }
    }
}