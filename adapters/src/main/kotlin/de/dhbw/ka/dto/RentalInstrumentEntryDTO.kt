package de.dhbw.ka.dto

import de.dhbw.ka.datatables.RentalEntriesTable
import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ResultRow

@Serializable
data class RentalInstrumentEntryDTO(
    var rentalId: Int = -1, val memberId: Int = -1, val instrumentIdentification: InstrumentIdentificationDTO
) {
    companion object RentalInstrumentEntryMapper {
        fun resultRowToRentalInstrumentEntryDTO(resultRow: ResultRow): RentalInstrumentEntryDTO {
            return RentalInstrumentEntryDTO(
                rentalId = resultRow[RentalEntriesTable.rentalId],
                memberId = resultRow[RentalEntriesTable.memberId],
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentManufacturer = resultRow[RentalEntriesTable.instrumentManufacturer],
                    instrumentSerialNumber = resultRow[RentalEntriesTable.instrumentSerialNumber],
                    instrumentType = resultRow[RentalEntriesTable.instrumentType]
                )
            )
        }

        fun toRentalInstrumentEntryDTO(
            instrumentRentalEntryData: InstrumentRentalEntry
        ): RentalInstrumentEntryDTO {
            val instrumentId = instrumentRentalEntryData.instrumentIdentification
            return RentalInstrumentEntryDTO(
                rentalId = instrumentRentalEntryData.rentalEntryId,
                memberId = instrumentRentalEntryData.memberId,
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentId.instrumentManufacturer,
                    instrumentId.instrumentSerialNumber,
                    instrumentId.instrumentType
                )
            )
        }

        fun toRentalInstrumentEntry(rentalInstrumentEntryDTOData: RentalInstrumentEntryDTO): InstrumentRentalEntry {
            val instrumentId = rentalInstrumentEntryDTOData.instrumentIdentification
            return InstrumentRentalEntry(
                rentalEntryId = rentalInstrumentEntryDTOData.rentalId,
                memberId = rentalInstrumentEntryDTOData.memberId,
                instrumentIdentification = InstrumentIdentification(
                    instrumentId.instrumentManufacturer,
                    instrumentId.instrumentSerialNumber,
                    instrumentId.instrumentType
                )
            )
        }

    }
}
