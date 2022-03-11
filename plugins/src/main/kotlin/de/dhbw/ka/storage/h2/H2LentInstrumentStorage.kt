package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.LentInstrumentsTable
import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.dto.LentInstrumentDTO
import de.dhbw.ka.dto.LentInstrumentDTO.LentInstrumentMapper.resultRowToLentInstrumentDTO
import de.dhbw.ka.storage.LentInstrumentStorage
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2LentInstrumentStorage : LentInstrumentStorage {
    override fun createRentalEntry(lentingEntryData: LentInstrumentDTO): Boolean {
        transaction {
            LentInstrumentsTable.insert {
                it[memberId] = lentingEntryData.memberId
                it[instrumentType] = lentingEntryData.instrumentIdentification.instrumentType
                it[instrumentManufacturer] = lentingEntryData.instrumentIdentification.instrumentManufacturer
                it[instrumentSerialNumber] = lentingEntryData.instrumentIdentification.instrumentSerialNumber
            }
        }
        return true
    }

    override fun getAllLentInstruments(): List<LentInstrumentDTO> {
        val rentedInstrumentsList: MutableList<LentInstrumentDTO> = mutableListOf()
        transaction {
            LentInstrumentsTable.selectAll().map {
                val mappedResult = resultRowToLentInstrumentDTO(it)
                rentedInstrumentsList.add(mappedResult)
            }
        }
        return rentedInstrumentsList
    }

    override fun getLentInstrumentsByMember(): LentInstrument {
        TODO("Not yet implemented")
    }
}
