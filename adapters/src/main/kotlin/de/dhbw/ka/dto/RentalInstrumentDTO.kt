package de.dhbw.ka.dto

import de.dhbw.ka.datatables.RentalInstrumentsTable
import de.dhbw.ka.domain.aggregates.RentalInstrument
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import org.jetbrains.exposed.sql.ResultRow

@kotlinx.serialization.Serializable
data class RentalInstrumentDTO(
    val instrumentIdentification: InstrumentIdentificationDTO,
    val quantity: Int
) {
    companion object RentalInstrumentMapper {
        fun resultRowToRentalInstrumentDTO(resultRow: ResultRow): RentalInstrumentDTO {
            return RentalInstrumentDTO(
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentManufacturer = resultRow[RentalInstrumentsTable.instrumentManufacturer],
                    instrumentSerialNumber = resultRow[RentalInstrumentsTable.instrumentSerialNumber],
                    instrumentType = resultRow[RentalInstrumentsTable.instrumentType],
                ),
                quantity = resultRow[RentalInstrumentsTable.quantity]
            )
        }

        fun toRentalInstrumentDTO(rentalInstrument: RentalInstrument): RentalInstrumentDTO {
            val instrumentId = rentalInstrument.instrumentIdentification
            return RentalInstrumentDTO(
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentManufacturer = instrumentId.instrumentManufacturer,
                    instrumentSerialNumber = instrumentId.instrumentSerialNumber,
                    instrumentType = instrumentId.instrumentType,
                ),
                quantity = rentalInstrument.quantity
            )
        }

        fun toRentalInstrument(rentalInstrumentDTO: RentalInstrumentDTO): RentalInstrument {
            val rentalInstrumentId = rentalInstrumentDTO.instrumentIdentification
            return RentalInstrument(
                instrumentIdentification = InstrumentIdentification(
                    instrumentManufacturer = rentalInstrumentId.instrumentManufacturer,
                    instrumentSerialNumber = rentalInstrumentId.instrumentSerialNumber,
                    instrumentType = rentalInstrumentId.instrumentType,
                ),
                quantity = rentalInstrumentDTO.quantity
            )
        }
    }
}
