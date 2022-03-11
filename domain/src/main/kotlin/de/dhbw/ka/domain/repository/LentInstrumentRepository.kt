package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification


interface LentInstrumentRepository {
    fun borrowInstrument(memberId: Int, instrumentToBeLent : InstrumentIdentification) : Boolean // TODO maybe InstrumentIdentification
    fun getLentInstrumentByMember(memberId: Int) : LentInstrument
    fun getAllLentInstruments() : List<LentInstrument>
}