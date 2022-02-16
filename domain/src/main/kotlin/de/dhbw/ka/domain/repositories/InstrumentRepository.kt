package de.dhbw.ka.domain.repositories

import de.dhbw.ka.domain.entities.Instrument

interface InstrumentRepository {

    suspend fun getInstrumentsInventory() : Result<List<Instrument>>

    suspend fun addInstrumentToInventory()

    suspend fun deleteInstrumentFromInventory()

}
