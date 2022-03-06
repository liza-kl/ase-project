package de.dhbw.ka.dto

import de.dhbw.ka.domain.entities.Instrument

@kotlinx.serialization.Serializable
data class InstrumentDTO(
    val instrumentType: String,
    val instrumentManufacturer: String,
    val instrumentSerialNumber: String,
    val instrumentCategory: String,
    val instrumentAmbitus: String
) {
    companion object InstrumentMapper {
        fun resultRowToInstrumentDTO() : Instrument {
            TODO("Not yet implemented")
        }
        fun toInstrumentDTO() : InstrumentDTO {
            TODO("Not yet implemented")
        }
        fun toInstrument() : Instrument {
            TODO("Not yet implemented")
        }
    }
}