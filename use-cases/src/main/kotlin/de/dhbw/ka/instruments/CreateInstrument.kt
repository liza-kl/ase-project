package de.dhbw.ka.instruments

import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.repository.InstrumentRepository

class CreateInstrument(private val instrumentRepository: InstrumentRepository) {
    fun execute(newInstrumentData: Instrument): Boolean {
        return instrumentRepository.createInstrument(instrumentData = newInstrumentData)
    }
}
