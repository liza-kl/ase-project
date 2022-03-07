package de.dhbw.ka.storage

import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.dto.InstrumentDTO
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

class H2InstrumentStorage : InstrumentStorage {
    override fun create(instrumentData: InstrumentDTO): Boolean {
        transaction {
            InstrumentTable.insert {
                it[instrumentType] = instrumentData.instrumentType
                it[instrumentSerialNumber] = instrumentData.instrumentSerialNumber
                it[instrumentManufacturer] = instrumentData.instrumentManufacturer
                it[instrumentCategory] = instrumentData.instrumentCategory
            }

        }
        return true
    }
    }
