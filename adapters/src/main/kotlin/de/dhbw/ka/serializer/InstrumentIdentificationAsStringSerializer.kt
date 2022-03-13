package de.dhbw.ka.serializer

import de.dhbw.ka.dto.InstrumentIdentificationDTO
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.*


object InstrumentIdentificationAsStringSerializer : KSerializer<InstrumentIdentificationDTO> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("InstrumentIdentification") {
            element<String>("instrumentManufacturer")
            element<String>("instrumentSerialNumber")
            element<String>("instrumentType")
        }

    override fun serialize(encoder: Encoder, value: InstrumentIdentificationDTO) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.instrumentManufacturer)
            encodeStringElement(descriptor, 1, value.instrumentSerialNumber)
            encodeStringElement(descriptor, 2, value.instrumentType)
        }
    }

    override fun deserialize(decoder: Decoder): InstrumentIdentificationDTO =
        decoder.decodeStructure(descriptor) {
            var instrumentManufacturer = ""
            var instrumentSerialNumber = ""
            var instrumentType = ""

            while (true) {
                when (val index = decodeElementIndex(descriptor)) {
                    0 -> instrumentManufacturer = decodeStringElement(descriptor, 0)
                    1 -> instrumentSerialNumber = decodeStringElement(descriptor, 1)
                    2 -> instrumentType = decodeStringElement(descriptor, 2)
                    CompositeDecoder.DECODE_DONE -> break
                    else -> error("Unexpected index: $index")
                }
            }
            InstrumentIdentificationDTO(instrumentManufacturer, instrumentSerialNumber, instrumentType)
        }
}

