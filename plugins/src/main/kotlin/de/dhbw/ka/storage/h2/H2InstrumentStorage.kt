package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.dto.InstrumentDTO
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.storage.interfaces.InstrumentStorage
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2InstrumentStorage : InstrumentStorage {
    override fun create(instrumentData: InstrumentDTO): Boolean {
        transaction {
            InstrumentTable.insert {
                it[instrumentManufacturer] = instrumentData.instrumentManufacturer
                it[instrumentSerialNumber] = instrumentData.instrumentSerialNumber
                it[instrumentType] = instrumentData.instrumentType
                it[instrumentCategory] = instrumentData.instrumentCategory
            }

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

    override fun checkIfInstrumentExists(instrumentIdentificationDTO: InstrumentIdentificationDTO): Boolean {
        transaction {
            InstrumentTable.select {
                (InstrumentTable.instrumentManufacturer eq instrumentIdentificationDTO.instrumentManufacturer)
                (InstrumentTable.instrumentSerialNumber eq instrumentIdentificationDTO.instrumentSerialNumber)
                (InstrumentTable.instrumentType eq instrumentIdentificationDTO.instrumentType)
            }.firstOrNull()
        } ?: return false
        return true
    }
}

