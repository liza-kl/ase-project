package de.dhbw.ka.dto

import de.dhbw.ka.datatables.LentInstrumentsTable
import de.dhbw.ka.domain.aggregates.LentInstrument
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import de.dhbw.ka.serializer.InstrumentIdentificationSerializer
import org.jetbrains.exposed.sql.ResultRow

@kotlinx.serialization.Serializable(with = InstrumentIdentificationSerializer::class)

data class LentInstrumentDTO(
    val lentingId: Int = -1, val memberId: Int = -1, val instrumentIdentification: InstrumentIdentification
) {
    companion object LentInstrumentMapper {
        fun resultRowToLentInstrumentDTO(resultRow: ResultRow): LentInstrumentDTO {
            return LentInstrumentDTO(
                lentingId = resultRow[LentInstrumentsTable.lentingId],
                memberId = resultRow[LentInstrumentsTable.memberId],
                instrumentIdentification = InstrumentIdentification(
                    instrumentManufacturer = resultRow[LentInstrumentsTable.instrumentManufacturer],
                    instrumentSerialNumber = resultRow[LentInstrumentsTable.instrumentSerialNumber],
                    instrumentType = resultRow[LentInstrumentsTable.instrumentType]
                ))
        }

        fun toLentInstrumentDTO(
            lentInstrumentData: LentInstrument
        ): LentInstrumentDTO {
            return LentInstrumentDTO(
                memberId = lentInstrumentData.memberId,
                instrumentIdentification = lentInstrumentData.instrumentIdentification
            )
        }

        fun toLentInstrument(lentInstrumentDTOData: LentInstrumentDTO): LentInstrument {
            return LentInstrument(
                memberId = lentInstrumentDTOData.memberId,
                instrumentIdentification = lentInstrumentDTOData.instrumentIdentification
            )
        }

    }
}
