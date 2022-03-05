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
        InstrumentCategoryOptions.valueOf(instrumentCategory)
    }
}