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
                    instrumentType = resultRow[RentalInstrumentsTable.instrumentType],
                    instrumentManufacturer = resultRow[RentalInstrumentsTable.instrumentManufacturer],
                    instrumentSerialNumber = resultRow[RentalInstrumentsTable.instrumentSerialNumber]
                ),
                quantity = resultRow[RentalInstrumentsTable.quantity]
            )
        }

        fun toRentalInstrumentDTO(rentalInstrument: RentalInstrument): RentalInstrumentDTO {
            return RentalInstrumentDTO(
                instrumentIdentification = InstrumentIdentificationDTO(
                    rentalInstrument.instrumentIdentification.instrumentType,
                    rentalInstrument.instrumentIdentification.instrumentManufacturer,
                    rentalInstrument.instrumentIdentification.instrumentSerialNumber
                ),
                quantity = rentalInstrument.quantity
            )
        }

        fun toRentalInstrument(rentalInstrumentDTO: RentalInstrumentDTO): RentalInstrument {
            return RentalInstrument(
                instrumentIdentification = InstrumentIdentification(
                    rentalInstrumentDTO.instrumentIdentification.instrumentType,
                    rentalInstrumentDTO.instrumentIdentification.instrumentManufacturer,
                    rentalInstrumentDTO.instrumentIdentification.instrumentSerialNumber,
                ),
                quantity = rentalInstrumentDTO.quantity
            )
        }
    }
}
