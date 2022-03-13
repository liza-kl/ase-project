package de.dhbw.ka.storage.h2

import de.dhbw.ka.datatables.RentalInstrumentsTable
import de.dhbw.ka.dto.InstrumentIdentificationDTO
import de.dhbw.ka.dto.RentalInstrumentDTO
import de.dhbw.ka.dto.RentalInstrumentDTO.RentalInstrumentMapper.resultRowToRentalInstrumentDTO
import de.dhbw.ka.storage.RentalInstrumentStorage
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.math.absoluteValue

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
                it[instrumentManufacturer] = rentalInstrumentData.instrumentIdentification.instrumentManufacturer
                it[instrumentSerialNumber] = rentalInstrumentData.instrumentIdentification.instrumentSerialNumber
                it[instrumentType] = rentalInstrumentData.instrumentIdentification.instrumentType
                it[quantity] = rentalInstrumentData.quantity
            }
        }
        return true
    }

    override fun checkIfRentalInstrumentExists(instrumentIdentificationDTO: InstrumentIdentificationDTO): Boolean {
        transaction {
            RentalInstrumentsTable.select {
                (RentalInstrumentsTable.instrumentManufacturer eq instrumentIdentificationDTO.instrumentManufacturer)
                (RentalInstrumentsTable.instrumentSerialNumber eq instrumentIdentificationDTO.instrumentSerialNumber)
                (RentalInstrumentsTable.instrumentType eq instrumentIdentificationDTO.instrumentType)
            }.firstOrNull()
        } ?: return false
        return true
    }

    override fun checkAvailableQuantity(instrumentIdentificationDTO: InstrumentIdentificationDTO): Int {
        val result = transaction {
            RentalInstrumentsTable
                .slice(RentalInstrumentsTable.quantity)
                .select {
                    (RentalInstrumentsTable.instrumentManufacturer eq instrumentIdentificationDTO.instrumentManufacturer)
                    (RentalInstrumentsTable.instrumentType eq instrumentIdentificationDTO.instrumentType)
                    (RentalInstrumentsTable.instrumentSerialNumber eq instrumentIdentificationDTO.instrumentSerialNumber)
                }.firstOrNull()
        }
        return (result?.get(RentalInstrumentsTable.quantity) as Int).absoluteValue // TODO Proper Casting!!!
    }

    override fun decreaseQuantity(instrumentIdentificationDTO: InstrumentIdentificationDTO) {
        transaction {
            RentalInstrumentsTable.update({
                (RentalInstrumentsTable.instrumentManufacturer eq instrumentIdentificationDTO.instrumentManufacturer)
                (RentalInstrumentsTable.instrumentType eq instrumentIdentificationDTO.instrumentType)
                (RentalInstrumentsTable.instrumentSerialNumber eq instrumentIdentificationDTO.instrumentSerialNumber)
            }) {
                with(SqlExpressionBuilder) {
                    it.update(quantity, quantity - 1)
                }
            }
        }
    }
}
