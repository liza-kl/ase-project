package de.dhbw.ka.storage

import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.dto.InstrumentIdentificationDTO

interface InstrumentStorage {
    fun create(instrumentData: InstrumentDTO) : Boolean
    fun getAllInstruments() : List<InstrumentDTO>
    fun checkIfInstrumentExists(instrumentIdentificationDTO: InstrumentIdentificationDTO) : Boolean
}