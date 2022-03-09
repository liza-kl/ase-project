package de.dhbw.ka.instruments

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository

class GetAllInstruments(private val instrumentRepository: InstrumentRepository) {
    fun execute(): List<Instrument> {
        return instrumentRepository.getAllInstruments()
    }
}