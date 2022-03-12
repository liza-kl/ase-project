package de.dhbw.ka.dto

import de.dhbw.ka.datatables.RentalEntriesTable
import de.dhbw.ka.domain.aggregates.InstrumentRentalEntry
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import org.jetbrains.exposed.sql.ResultRow
import kotlinx.serialization.*

@Serializable
data class RentalInstrumentDTO(
    var rentalId: Int = -1, val memberId: Int = -1, val instrumentIdentification: InstrumentIdentificationDTO
) {
    companion object RentalInstrumentMapper {
        fun resultRowToRentalInstrumentDTO(resultRow: ResultRow): RentalInstrumentDTO {
            return RentalInstrumentDTO(
                rentalId = resultRow[RentalEntriesTable.rentalId],
                memberId = resultRow[RentalEntriesTable.memberId],
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentManufacturer = resultRow[RentalEntriesTable.instrumentManufacturer],
                    instrumentSerialNumber = resultRow[RentalEntriesTable.instrumentSerialNumber],
                    instrumentType = resultRow[RentalEntriesTable.instrumentType]
                )
            )
        }

        fun toRentalInstrumentDTO(
            instrumentRentalEntryData: InstrumentRentalEntry
        ): RentalInstrumentDTO {
            return RentalInstrumentDTO(
                rentalId = instrumentRentalEntryData.rentalEntryId,
                memberId = instrumentRentalEntryData.memberId,
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentRentalEntryData.instrumentIdentification.instrumentManufacturer,
                    instrumentRentalEntryData.instrumentIdentification.instrumentSerialNumber,
                    instrumentRentalEntryData.instrumentIdentification.instrumentType
                )
            )
        }

        fun toRentalInstrument(rentalInstrumentDTOData: RentalInstrumentDTO): InstrumentRentalEntry {
            return InstrumentRentalEntry(
                rentalEntryId = rentalInstrumentDTOData.rentalId,
                memberId = rentalInstrumentDTOData.memberId,
                instrumentIdentification = InstrumentIdentification(
                    rentalInstrumentDTOData.instrumentIdentification.instrumentSerialNumber,
                    rentalInstrumentDTOData.instrumentIdentification.instrumentManufacturer,
                    rentalInstrumentDTOData.instrumentIdentification.instrumentType
                )
            )
        }

    }
}
