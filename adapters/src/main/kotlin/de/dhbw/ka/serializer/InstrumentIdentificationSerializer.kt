package de.dhbw.ka.serializer

import de.dhbw.ka.domain.valueobjects.InstrumentIdentification
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.*
import kotlinx.serialization.encoding.*


object InstrumentIdentificationSerializer : KSerializer<InstrumentIdentification> {
    override val descriptor: SerialDescriptor =
        buildClassSerialDescriptor("InstrumentIdentification") {
            element<String>("instrumentManufacturer")
            element<String>("instrumentSerialNumber")
            element<String>("instrumentType")
        }

    override fun serialize(encoder: Encoder, value: InstrumentIdentification) {
        encoder.encodeStructure(descriptor) {
            encodeStringElement(descriptor, 0, value.instrumentType)
            encodeStringElement(descriptor, 1, value.instrumentManufacturer)
            encodeStringElement(descriptor, 2, value.instrumentSerialNumber)
        }
    }

    override fun deserialize(decoder: Decoder): InstrumentIdentification =
        decoder.decodeStructure(descriptor) {
        var instrumentType = ""
        var instrumentManufacturer = ""
        var instrumentSerialNumber = ""

        while (true) {
            when (val index = decodeElementIndex(descriptor)) {
                0 -> instrumentType = decodeStringElement(descriptor, 0)
                1 -> instrumentManufacturer = decodeStringElement(descriptor, 1)
                2 -> instrumentSerialNumber = decodeStringElement(descriptor, 2)
                CompositeDecoder.DECODE_DONE -> break
                else -> error("Unexpected index: $index")
            }
        }
            InstrumentIdentification(instrumentManufacturer,instrumentSerialNumber, instrumentType)
    }
}

