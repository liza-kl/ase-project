package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.RentalEntriesTable
import de.dhbw.ka.dto.RentalInstrumentEntryDTO
import de.dhbw.ka.dto.RentalInstrumentEntryDTO.RentalInstrumentEntryMapper.resultRowToRentalInstrumentEntryDTO
import de.dhbw.ka.storage.interfaces.InstrumentRentalEntryStorage
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2InstrumentRentalEntryStorage : InstrumentRentalEntryStorage {
    override fun createRentalEntry(rentalEntryData: RentalInstrumentEntryDTO): Boolean {
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

    override fun getAllInstrumentRentalEntries(): List<RentalInstrumentEntryDTO> {
        val rentedInstrumentsList: MutableList<RentalInstrumentEntryDTO> = mutableListOf()
        transaction {
            RentalEntriesTable.selectAll().map {
                val mappedResult = resultRowToRentalInstrumentEntryDTO(it)
                rentedInstrumentsList.add(mappedResult)
            }
        }
        return rentedInstrumentsList
    }

}
