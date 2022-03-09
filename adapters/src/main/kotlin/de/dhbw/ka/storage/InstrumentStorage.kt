package de.dhbw.ka.storage

import de.dhbw.ka.dto.InstrumentDTO

interface InstrumentStorage {
    fun create(instrumentData: InstrumentDTO) : Boolean
    fun getAllInstruments() : List<InstrumentDTO>
}