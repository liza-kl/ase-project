package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.storage.InstrumentStorage
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2InstrumentStorage : InstrumentStorage {
    override fun create(instrumentData: InstrumentDTO): Boolean {
        transaction {
            InstrumentTable.insert  {
                it[instrumentType] = instrumentData.instrumentType
                it[instrumentManufacturer] = instrumentData.instrumentManufacturer
                it[instrumentSerialNumber] = instrumentData.instrumentSerialNumber
                it[instrumentCategory] = instrumentData.instrumentCategory
            } get InstrumentTable.storageId

        }
        return true
    }

    override fun getAllInstruments(): List<InstrumentDTO> {
        val instrumentsList = mutableListOf<InstrumentDTO>()
        transaction {
            InstrumentTable.selectAll().map {
                val mappedResult = InstrumentDTO.resultRowToInstrumentDTO(it)
                instrumentsList.add(mappedResult)
            }
        }
        return instrumentsList
    }
}

