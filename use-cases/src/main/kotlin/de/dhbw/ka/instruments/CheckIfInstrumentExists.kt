package de.dhbw.ka.instruments

import de.dhbw.ka.domain.repository.InstrumentRepository
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification

class CheckIfInstrumentExists(private val instrumentRepository: InstrumentRepository) {
    fun execute(instrumentToCheck: InstrumentIdentification) : Boolean {
        if (instrumentRepository.checkIfInstrumentExists(instrumentToCheck)) {
            return true
        }
        return false
    }
}