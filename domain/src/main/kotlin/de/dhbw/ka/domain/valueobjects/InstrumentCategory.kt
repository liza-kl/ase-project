package de.dhbw.ka.domain.valueobjects

enum class InstrumentCategoryOptions {
    WOODWIND,
    BRASS,
    PERCUSSION,
    KEYBOARD,
    STRING,
    GUITAR
}

@JvmInline
value class InstrumentCategory(val instrumentCategory: String) {
    init {
        require(InstrumentCategoryOptions.valueOf(instrumentCategory).toString() == instrumentCategory) {
            throw IllegalArgumentException("Please provide a valid Instrument Category.")
        }
    }
}
