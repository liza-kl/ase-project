package de.dhbw.ka.domain.valueobjects

@JvmInline
value class InstrumentType(val instrumentType: String) {
    init {
        require(instrumentType.isNotEmpty())
    }
}