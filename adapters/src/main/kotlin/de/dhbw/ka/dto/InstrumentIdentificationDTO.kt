package de.dhbw.ka.dto

import de.dhbw.ka.serializer.InstrumentIdentificationAsStringSerializer
import kotlinx.serialization.Serializable

@Serializable(with = InstrumentIdentificationAsStringSerializer::class)
data class InstrumentIdentificationDTO(
    val instrumentManufacturer: String,
    val instrumentSerialNumber: String,
    val instrumentType: String
)