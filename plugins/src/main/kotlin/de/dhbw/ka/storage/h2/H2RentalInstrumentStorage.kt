package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.RentalInstrumentsTable
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.resultRowToRentalInstrumentDTO
import de.dhbw.ka.storage.RentalInstrumentStorage
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

class H2RentalInstrumentStorage : RentalInstrumentStorage {
    override fun getAllRentalInstruments(): List<RentalInstrumentDTO> {
        val rentalInstrumentList = mutableListOf<RentalInstrumentDTO>()
        transaction {
            RentalInstrumentsTable.selectAll().map {
                val mappedResult = resultRowToRentalInstrumentDTO(it)
                rentalInstrumentList.add(mappedResult)
            }
        }
        return rentalInstrumentList
    }

    override fun createRentalInstrument(rentalInstrumentData: RentalInstrumentDTO): Boolean {
        transaction {
            RentalInstrumentsTable.insert {
                it[instrumentType] = rentalInstrumentData.instrumentIdentification.instrumentType
                it[instrumentSerialNumber] = rentalInstrumentData.instrumentIdentification.instrumentSerialNumber
                it[instrumentManufacturer] = rentalInstrumentData.instrumentIdentification.instrumentManufacturer
                it[quantity] = rentalInstrumentData.quantity
            }
        }
        return true
    }

    override fun checkIfRentalInstrumentExists(instrumentIdentificationDTO: InstrumentIdentificationDTO): Boolean {
        transaction {
            RentalInstrumentsTable.select {
                (RentalInstrumentsTable.instrumentSerialNumber eq instrumentIdentificationDTO.instrumentSerialNumber)
            }.firstOrNull()
        } ?: return false
        return true
    }
}
