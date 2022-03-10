package de.dhbw.ka.dto

import de.dhbw.ka.datatables.LentInstrumentsTable
import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.serializer.InstrumentIdentificationAsStringSerializer
import org.jetbrains.exposed.sql.ResultRow
import kotlinx.serialization.*

@Serializable(with = InstrumentIdentificationAsStringSerializer::class)
data class InstrumentIdentificationDTO(
    val instrumentManufacturer: String,
    val instrumentSerialNumber: String,
    val instrumentType: String
)

@Serializable
data class LentInstrumentDTO(
    val lentingId: Int = -1, val memberId: Int = -1, val instrumentIdentification: InstrumentIdentificationDTO
) {
    companion object LentInstrumentMapper {
        fun resultRowToLentInstrumentDTO(resultRow: ResultRow): LentInstrumentDTO {
            return LentInstrumentDTO(
                lentingId = resultRow[LentInstrumentsTable.lentingId],
                memberId = resultRow[LentInstrumentsTable.memberId],
                instrumentIdentification = InstrumentIdentificationDTO(
                    instrumentManufacturer = resultRow[LentInstrumentsTable.instrumentManufacturer],
                    instrumentSerialNumber = resultRow[LentInstrumentsTable.instrumentSerialNumber],
                    instrumentType = resultRow[LentInstrumentsTable.instrumentType]
                )
            )
        }

        fun toLentInstrumentDTO(
            lentInstrumentData: LentInstrument
        ): LentInstrumentDTO {
            return LentInstrumentDTO(
                memberId = lentInstrumentData.memberId,
                instrumentIdentification = InstrumentIdentificationDTO(
                    lentInstrumentData.instrumentIdentification.instrumentManufacturer,
                    lentInstrumentData.instrumentIdentification.instrumentSerialNumber,
                    lentInstrumentData.instrumentIdentification.instrumentType
                )
            )
        }

        fun toLentInstrument(lentInstrumentDTOData: LentInstrumentDTO): LentInstrument {
            return LentInstrument(
                memberId = lentInstrumentDTOData.memberId,
                instrumentIdentification = InstrumentIdentification(
                    lentInstrumentDTOData.instrumentIdentification.instrumentSerialNumber,
                    lentInstrumentDTOData.instrumentIdentification.instrumentManufacturer,
                    lentInstrumentDTOData.instrumentIdentification.instrumentType
                )
            )
        }

    }
}