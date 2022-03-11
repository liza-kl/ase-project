package de.dhbw.ka.lentinstruments

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.repository.LentInstrumentRepository

class GetAllLentInstruments(private val lentInstrumentRepository: LentInstrumentRepository) {
    fun execute() : List<LentInstrument> {
        return lentInstrumentRepository.getAllLentInstruments()
    }
}