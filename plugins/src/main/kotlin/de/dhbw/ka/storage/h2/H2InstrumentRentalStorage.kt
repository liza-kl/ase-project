package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.RentalEntriesTable
import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.resultRowToRentalInstrumentDTO
import de.dhbw.ka.storage.InstrumentRentalStorage
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2InstrumentRentalStorage : InstrumentRentalStorage {
    override fun createRentalEntry(rentalEntryData: RentalInstrumentDTO): Boolean {
        transaction {
            RentalEntriesTable.insert {
                it[memberId] = rentalEntryData.memberId
                it[instrumentType] = rentalEntryData.instrumentIdentification.instrumentType
                it[instrumentManufacturer] = rentalEntryData.instrumentIdentification.instrumentManufacturer
                it[instrumentSerialNumber] = rentalEntryData.instrumentIdentification.instrumentSerialNumber
            }
        }
        return true
    }

    override fun getAllInstrumentRentalEntries(): List<RentalInstrumentDTO> {
        val rentedInstrumentsList: MutableList<RentalInstrumentDTO> = mutableListOf()
        transaction {
            RentalEntriesTable.selectAll().map {
                val mappedResult = resultRowToRentalInstrumentDTO(it)
                rentedInstrumentsList.add(mappedResult)
            }
        }
        return rentedInstrumentsList
    }

    override fun getInstrumentsRentedByMember(): InstrumentRentalEntry {
        TODO("Not yet implemented")
    }
}
