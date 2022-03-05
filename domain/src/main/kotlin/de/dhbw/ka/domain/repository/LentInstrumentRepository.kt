package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.entities.Instrument


interface LentInstrumentRepository {
    fun borrowInstrument(memberId: Int, instrumentToBeLent : Instrument) : Boolean // TODO maybe InstrumentIdentification
    fun getLentInstrumentByMember(memberId: Int) : LentInstrument
}