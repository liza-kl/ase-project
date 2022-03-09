package de.dhbw.ka.dto

import de.dhbw.ka.datatables.InstrumentTable
import de.dhbw.ka.domain.entities.Instrument
import de.dhbw.ka.domain.valueobjects.InstrumentCategory
import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import org.jetbrains.exposed.sql.ResultRow
import kotlinx.serialization.Serializable

@Serializable
data class InstrumentDTO(
    val instrumentType: String,
    val instrumentManufacturer: String,
    val instrumentSerialNumber: String,
    val instrumentCategory: String,
) {
    companion object InstrumentMapper {
        fun resultRowToInstrumentDTO(resultRow: ResultRow): InstrumentDTO {
            return InstrumentDTO(
                instrumentType = resultRow[InstrumentTable.instrumentType],
                instrumentManufacturer = resultRow[InstrumentTable.instrumentManufacturer],
                instrumentSerialNumber = resultRow[InstrumentTable.instrumentSerialNumber],
                instrumentCategory = resultRow[InstrumentTable.instrumentCategory],
            )
        }

        fun toInstrumentDTO(instrumentEntity: Instrument): InstrumentDTO {
            val instrumentIdentification = instrumentEntity.instrumentIdentification
            return InstrumentDTO(
                instrumentType = instrumentIdentification.instrumentType,
                instrumentManufacturer = instrumentIdentification.instrumentManufacturer,
                instrumentSerialNumber = instrumentIdentification.instrumentSerialNumber,
                instrumentCategory = instrumentEntity.instrumentCategory.instrumentCategory,
            )
        }

        fun toInstrument(instrumentDTO: InstrumentDTO): Instrument {
            return Instrument(
                instrumentIdentification = InstrumentIdentification(
                    instrumentManufacturer = instrumentDTO.instrumentManufacturer,
                    instrumentSerialNumber = instrumentDTO.instrumentSerialNumber,
                    instrumentType = instrumentDTO.instrumentType
                ),
                instrumentCategory = InstrumentCategory(
                    instrumentCategory = instrumentDTO.instrumentCategory
                )
            )
        }
    }
}