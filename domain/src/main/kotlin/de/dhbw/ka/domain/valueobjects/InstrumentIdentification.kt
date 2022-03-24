package de.dhbw.ka.domain.valueobjects

data class InstrumentIdentification(
    val instrumentManufacturer: String,
    val instrumentSerialNumber: String,
    val instrumentType: String,
) {
    init {
        require(instrumentType.isNotEmpty()
                && instrumentManufacturer.isNotEmpty()
                && instrumentSerialNumber.isNotEmpty()) {
            throw IllegalArgumentException("Please provide data for every property of the desired Instrument")
        }
    }
}
